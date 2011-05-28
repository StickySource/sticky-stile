package net.stickycode.stile.version;

class StringVersionComponent
    extends VersionComponent<String> {

  private final String value;

  public StringVersionComponent(String value) {
    this.value = Prerequisites.notBlank(value, "String versions cannot be blank");
  }

  @Override
  protected String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

}
