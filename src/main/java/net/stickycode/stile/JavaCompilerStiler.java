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

import net.stickycode.resource.DirectoryResources;
import net.stickycode.resource.FileResources;
import net.stickycode.resource.ResourceType;
import net.stickycode.resource.Resources;

public class JavaCompilerStiler {

  // @Configured("The directory where the classes will be compiled to")
  // File outputDirectory = new File("stile/classes");

  @Inject
  private Workspace workspace;

  // @Processes("text/x-java-source")
  // @Generates("application/java-vm")
  // <ContentType.JavaByteCode>
  public Resources process(Resources sources) {
    File outputDirectory = getOutputDirectory(sources);
    compileSourceInto(sources, outputDirectory);
    return new DirectoryResources(outputDirectory, new ResourceType(".class"));
  }

  private void compileSourceInto(Resources sources, File outputDirectory) {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager fileManager = getFileManager(outputDirectory, compiler);
    try {
      Iterable<? extends JavaFileObject> compilationUnits1 =
          fileManager.getJavaFileObjectsFromFiles(new FileResources(sources));
      Boolean result = compiler.getTask(null, fileManager, null, null, null, compilationUnits1).call();
      if (!result)
        throw new RuntimeException("oops");
    }
    finally {
      closeQuietly(fileManager);
    }
  }

  protected File getOutputDirectory(Resources resources) {
    return new File(workspace.getOutputDirectory(), "classes/" + resources.getReference());
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

  // private Resources selectOutOfDateResources(Resources sources) {
  // return classResources();
  // return null;
  // }
  //
  // private Resources classResources(Resources sources) {
  // // new ResourcesBuilder()
  // // .fromPath("target", "classes");
  // return null;
  // }

}
