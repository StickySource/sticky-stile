package net.stickycode.stile.version;

import java.util.Iterator;
import java.util.List;

public class Version
  implements Iterable<VersionComponent<?>>{

  private final List<VersionComponent<?>> components;

  Version(List<VersionComponent<?>> components) {
    super();
    this.components = components;
  }

  @Override
  public Iterator<VersionComponent<?>> iterator() {
    return components.iterator();
  }

}
