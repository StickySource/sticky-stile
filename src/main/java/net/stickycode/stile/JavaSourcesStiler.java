package net.stickycode.stile;

import java.io.File;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.stickycode.resource.Resources;
import net.stickycode.resource.directory.DirectoryResources;
import net.stickycode.stereotype.Configured;
import net.stickycode.stile.sphere.Spheres;


public class JavaSourcesStiler {
  
  private Logger log = LoggerFactory.getLogger(getClass());

  @Inject
  Workspace workspace;

  @Configured("The scope of the sources being collected")
  Spheres sphere; // TODO use Sphere

  @Produces(ResourcesTypes.JavaSource)
  public Resources collectSources() {
    File path = workspace.getSourcePath(sphere, "java");
    log.info("collecting sources in {}", path);
    return new DirectoryResources(path, ResourcesTypes.JavaSource);
  }

}
