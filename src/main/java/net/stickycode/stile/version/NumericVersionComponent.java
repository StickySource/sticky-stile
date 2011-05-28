package net.stickycode.stile.version;

import static net.stickycode.stile.version.Prerequisites.notNull;

class NumericVersionComponent
    extends VersionComponent<Integer> {

  private Integer value;

  public NumericVersionComponent(Integer value) {
    this.value = notNull(value, "Numeric versions cannot be null");
  }

  @Override
  protected Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

}
