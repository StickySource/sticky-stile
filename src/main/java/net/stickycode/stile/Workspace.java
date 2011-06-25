package net.stickycode.stile;

import java.io.File;

import net.stickycode.stile.artifact.Sphere;

public interface Workspace {

  // static {
  // getClass().getClassLoader().loadClass("org.eclipse.jdt.junit.TestRunListener");
  // }

  // TODO use resources?
  File getOutputDirectory();

  // TODO use resources?
  File getSourceDirectory();

  File getSourcePath(Sphere sphere, String string);

  File getOutputPath(Sphere sphere, String string);

}
