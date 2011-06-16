package net.stickycode.stile;

public enum Spheres
    implements Sphere {

  main("Sphere for contributers to the primary artifact"),
  test("Sphere for unit testing the primary artifact"),
  mix("Sphere for integration testing the primary artifact"),
  mingle("Sphere for component testing the primary artifact");

  private String description;

  private Spheres(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String getName() {
    return name();
  }

}
