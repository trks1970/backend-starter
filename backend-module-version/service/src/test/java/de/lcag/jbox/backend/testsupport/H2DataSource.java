package de.lcag.jbox.backend.testsupport;

import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

public abstract class H2DataSource {
  protected abstract String getSchemaAction();

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .setName(
            "testdb;DATABASE_TO_LOWER=TRUE" // ;DB_CLOSE_ON_EXIT=FALSE
            )
        .build();
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory);
    return txManager;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("de.lcag.jbox");
    factory.setPersistenceUnitName("test");
    factory.setDataSource(dataSource());
    factory.setJpaProperties(hibernateProperties());
    return factory;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("jakarta.persistence.schema-generation.database.action", getSchemaAction());
    properties.put(
        AvailableSettings.PHYSICAL_NAMING_STRATEGY,
        CamelCaseToUnderscoresNamingStrategy.class.getName());
    properties.put(
        AvailableSettings.IMPLICIT_NAMING_STRATEGY, SpringImplicitNamingStrategy.class.getName());
    properties.put("hibernate.integration.envers.enabled", true);
    properties.put("store_data_at_delete", true);
    properties.put("global_with_modified_flag", true);
    properties.put("org.hibernate.envers.do_not_audit_optimistic_locking_field", true);
    return properties;
  }
}
