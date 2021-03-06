package net.stickycode.stile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import javax.inject.Inject;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.stickycode.resource.Resources;
import net.stickycode.resource.directory.DirectoryResources;
import net.stickycode.resource.directory.FilesFromResources;
import net.stickycode.stereotype.Configured;
import net.stickycode.stile.sphere.Spheres;

public class JavaCompilerStiler {

  private Logger log = LoggerFactory.getLogger(getClass());
  
  // @Configured("The directory where the classes will be compiled to")
  // File outputDirectory = new File("stile/classes");

  @Inject
  private Workspace workspace;
  
  @Configured
  private Spheres sphere;

  @Processes(ResourcesTypes.JavaSource)
  @Produces(ResourcesTypes.JavaByteCode)
  public Resources process(Resources sources) {
    File outputDirectory = getOutputDirectory(sources);
    log.info("compiling to {}", outputDirectory);
    compileSourceInto(sources, outputDirectory);
    return new DirectoryResources(outputDirectory, ResourcesTypes.JavaByteCode);
  }

  private void compileSourceInto(Resources sources, File outputDirectory) {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager fileManager = getFileManager(outputDirectory, compiler);
    try {
      Iterable<? extends JavaFileObject> compilationUnits1 =
          fileManager.getJavaFileObjectsFromFiles(new FilesFromResources(sources));
      Boolean result = compiler.getTask(null, fileManager, null, null, null, compilationUnits1).call();
      if (!result)
        throw new RuntimeException("oops");
    }
    finally {
      closeQuietly(fileManager);
    }
  }

  protected File getOutputDirectory(Resources resources) {
    return workspace.getOutputPath(sphere, "classes");
  }

  private StandardJavaFileManager getFileManager(File outputDirectory, JavaCompiler compiler) {
    StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(null, null, null);
    try {
      outputDirectory.mkdirs();
      standardFileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singleton(outputDirectory));
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    return standardFileManager;
  }

  private void closeQuietly(StandardJavaFileManager fileManager) {
    try {
      fileManager.close();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
