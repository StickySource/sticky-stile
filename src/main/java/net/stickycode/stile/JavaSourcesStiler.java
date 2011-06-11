package net.stickycode.stile;

import java.io.File;

import net.stickycode.resource.Resources;
import net.stickycode.resource.directory.DirectoryResources;


public class JavaSourcesStiler {

  @Produces(ResourcesTypes.JavaSource)
  public Resources collectSources() {
    return new DirectoryResources(new File("src/test/java"), ResourcesTypes.JavaSource);
  }

}
