package net.stickycode.stile;

import java.io.File;

public interface Workspace {

  // static {
  // getClass().getClassLoader().loadClass("org.eclipse.jdt.junit.TestRunListener");
  // }

  // TODO use resources?
  File getOutputDirectory();

  // TODO use resources?
  File getSourceDirectory();

  /**
   *
   * @param sphere The context of the sources
   * @param string
   * @return
   */
  File getSourcePath(Sphere sphere, String string);

}
