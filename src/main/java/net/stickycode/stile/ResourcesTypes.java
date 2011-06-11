package net.stickycode.stile;

import net.stickycode.resource.ResourceType;

public enum ResourcesTypes implements ResourceType {
  JavaSource(".java"),
  JavaByteCode(".class");

  private String[] extentions;

  private ResourcesTypes(String... extentions) {
    this.extentions = extentions;
  }

  @Override
  public String[] getExtensions() {
    return extentions;
  }

}
