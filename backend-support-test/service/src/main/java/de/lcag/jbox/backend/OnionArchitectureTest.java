package de.lcag.jbox.backend;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import java.util.HashMap;
import java.util.Map;

/** Check clean code architecture and layer violations. */
@AnalyzeClasses(
    packages = OnionArchitectureTest.ROOT_PACKAGE,
    importOptions = ImportOption.DoNotIncludeTests.class)
public class OnionArchitectureTest {
  static final String ROOT_PACKAGE = "de.lcag.jbox.backend";

  @ArchTest
  static final ArchRule onionArchitectureIsRespected =
      Architectures.onionArchitecture()
          .withOptionalLayers(true)
          .domainModels(ROOT_PACKAGE + ".domain.model..")
          .domainServices(ROOT_PACKAGE + ".domain.service..", ROOT_PACKAGE + ".domain.repository..")
          .applicationServices(ROOT_PACKAGE + ".config..")
          .adapter("infrastructure", ROOT_PACKAGE + ".infrastructure..")
          .adapter("api", ROOT_PACKAGE + ".api..");

  @ArchTest
  static final ArchRule layerDependenciesAreRespected =
      Architectures.layeredArchitecture()
          .consideringAllDependencies()
          .withOptionalLayers(true)
          .layer("config")
          .definedBy(ROOT_PACKAGE + ".config..")
          .layer("domain")
          .definedBy(ROOT_PACKAGE + ".domain..")
          .layer("infrastructure")
          .definedBy(ROOT_PACKAGE + ".infrastructure..")
          .layer("api")
          .definedBy(ROOT_PACKAGE + ".api..")
          .whereLayer("config")
          .mayNotBeAccessedByAnyLayer()
          .whereLayer("domain")
          .mayOnlyBeAccessedByLayers("infrastructure", "api", "config")
          .whereLayer("infrastructure")
          .mayNotBeAccessedByAnyLayer()
          .whereLayer("api")
          .mayNotBeAccessedByAnyLayer();

  @ArchTest
  static final ArchRule layerApiIsConsistent =
      Architectures.layeredArchitecture()
          .consideringAllDependencies()
          .withOptionalLayers(true)
          .layer("exception")
          .definedBy(ROOT_PACKAGE + ".api.exception..")
          .optionalLayer("mapper")
          .definedBy(ROOT_PACKAGE + ".api.mapper..")
          .layer("resources")
          .definedBy(ROOT_PACKAGE + ".api.resources..")
          .layer("controller")
          .definedBy(ROOT_PACKAGE + ".api.controller..")
          .whereLayer("exception")
          .mayNotBeAccessedByAnyLayer() // GlobalExceptionHandler only inside
          .whereLayer("mapper")
          .mayOnlyBeAccessedByLayers("controller" /*, "exception"*/)
          .whereLayer("resources")
          .mayOnlyBeAccessedByLayers("mapper", "controller", "exception")
          .whereLayer("controller")
          .mayNotBeAccessedByAnyLayer();

  @ArchTest
  static final ArchRule layerInfrastructureIsConsistent =
      Architectures.layeredArchitecture()
          .consideringAllDependencies()
          .withOptionalLayers(true)
          .layer("entity")
          .definedBy(ROOT_PACKAGE + ".infrastructure.entity..")
          .layer("repository")
          .definedBy(ROOT_PACKAGE + ".infrastructure.repository..")
          .layer("mapper")
          .definedBy(ROOT_PACKAGE + ".infrastructure.mapper..")
          .whereLayer("entity")
          .mayOnlyBeAccessedByLayers("repository", "mapper")
          .whereLayer("mapper")
          .mayOnlyBeAccessedByLayers("repository")
          .whereLayer("repository")
          .mayNotBeAccessedByAnyLayer();

  /**
   * Will only cover direct byte code dependencies, but not for example data return type in domain
   * interfaces, like e.g. org.springframework.data.domain.Page.
   */
  @ArchTest
  static final ArchRule domainLayerIsIndependentFromExternalLibraries =
      ArchRuleDefinition.noClasses()
          .that()
          .haveNameMatching(ROOT_PACKAGE + ".domain.*")
          .should()
          .accessClassesThat()
          .haveNameMatching("org.springframework.*")
          .orShould()
          .accessClassesThat()
          .haveNameMatching("com.fasterxml.*")
          .because("Domain layer should not leak infrastructure libraries.");

  static final ArchCondition<JavaClass> haveAnUniqueName =
      new ArchCondition<>("have an unique class name") {
        private final Map<String, String> simpleNameToFullClassName = new HashMap<>();

        @Override
        public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
          String simpleClassName = javaClass.getSimpleName();
          if (simpleNameToFullClassName.containsKey(simpleClassName)
              && !"package-info".equals(simpleClassName)) {
            String message =
                String.format(
                    "Duplicate class name %s already exists here: %s and here: %s",
                    simpleClassName,
                    javaClass.getName(),
                    simpleNameToFullClassName.get(simpleClassName));
            conditionEvents.add(SimpleConditionEvent.violated(javaClass, message));
          }
          simpleNameToFullClassName.put(simpleClassName, javaClass.getName());
        }
      };

  @ArchTest
  static final ArchRule classNamesShouldBeUniqueAcrossLayers =
      ArchRuleDefinition.classes()
          .that()
          .areNotInnerClasses()
          .and()
          .areNotMemberClasses()
          .and()
          .areNotAnnotatedWith("jakarta.xml.bind.annotation.XmlRegistry")
          .and()
          .areNotAnnotatedWith("jakarta.xml.bind.annotation.XmlType")
          .should(haveAnUniqueName);
}
