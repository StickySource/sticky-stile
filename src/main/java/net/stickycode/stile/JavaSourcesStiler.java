package net.stickycode.stile;

import java.io.File;

import javax.inject.Inject;

import net.stickycode.resource.Resources;
import net.stickycode.resource.directory.DirectoryResources;
import net.stickycode.stereotype.Configured;


public class JavaSourcesStiler {

  @Inject
  Workspace workspace;

  @Configured("The scope of the sources being collected")
  Spheres sphere; // TODO use Sphere

  @Produces(ResourcesTypes.JavaSource)
  public Resources collectSources() {
    File path = workspace.getSourcePath(sphere, "java");
    return new DirectoryResources(path, ResourcesTypes.JavaSource);
  }

}
