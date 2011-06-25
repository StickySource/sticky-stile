package net.stickycode.stile;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.stickycode.stereotype.Configured;
import net.stickycode.stereotype.PostConfigured;
import net.stickycode.stile.artifact.Sphere;


public class CommandLineWorkspace
    implements Workspace {
  
  private Logger log = LoggerFactory.getLogger(getClass());
  
  @Configured
  File sourceDirectory;
  
  @Configured 
  File outputDirectory;
  
  @PostConfigured
  public void postConfigured() {
    if (!sourceDirectory.isDirectory())
      throw new RuntimeException(sourceDirectory.getAbsolutePath());
    
    if (!outputDirectory.isDirectory())
      if (!outputDirectory.mkdirs())
        throw new RuntimeException("Output directory did not exist can cannot be created");
    
    log.debug("workspace has source {} and output {}", sourceDirectory, outputDirectory);
  }

  @Override
  public File getOutputDirectory() {
    return sourceDirectory;
  }

  @Override
  public File getSourceDirectory() {
    return null;
  }

  @Override
  public File getSourcePath(Sphere sphere, String type) {
    return new File(sourceDirectory, sphere.getName() + "/" + type);
  }

  @Override
  public File getOutputPath(Sphere sphere, String type) {
    return new File(outputDirectory, sphere.getName() + "/" + type);
  }

}
