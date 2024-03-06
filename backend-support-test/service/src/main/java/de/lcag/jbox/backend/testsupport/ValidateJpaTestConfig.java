package backend.testsupport;

public class ValidateJpaTestConfig extends H2DataSource {

  @Override
  protected String getSchemaAction() {
    return "validate";
  }
}
