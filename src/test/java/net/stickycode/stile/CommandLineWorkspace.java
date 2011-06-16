package net.stickycode.stile;

import java.io.File;

import net.stickycode.stereotype.Configured;


public class CommandLineWorkspace
    implements Workspace {
  
  @Configured
  File sourceDirectory;
  
  @Configured 
  File outputDirectory;

  @Override
  public File getOutputDirectory() {
    return null;
  }

  @Override
  public File getSourceDirectory() {
    return null;
  }

  @Override
  public File getSourcePath(Sphere sphere, String type) {
    return new File(sourceDirectory, sphere.getName() + "/" + type);
  }

}
