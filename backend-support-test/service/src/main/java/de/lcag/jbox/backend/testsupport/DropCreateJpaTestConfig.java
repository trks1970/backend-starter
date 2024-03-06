package backend.testsupport;

public class DropCreateJpaTestConfig extends H2DataSource {

  @Override
  protected String getSchemaAction() {
    return "drop-and-create";
  }
}
