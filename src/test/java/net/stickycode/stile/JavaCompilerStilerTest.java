package net.stickycode.stile;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;

import net.stickycode.mockwire.Uncontrolled;
import net.stickycode.mockwire.UnderTest;
import net.stickycode.mockwire.junit4.MockwireRunner;
import net.stickycode.resource.Resources;
import net.stickycode.resource.directory.DirectoryResources;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockwireRunner.class)
public class JavaCompilerStilerTest {

  @Uncontrolled({"sourceDirectory=src", "outputDirectory=target/stile"})
  CommandLineWorkspace workspace;

  @UnderTest("sphere=mingle")
  JavaCompilerStiler compiler;

  @Test
  public void compile() throws ClassNotFoundException {
    Resources sources = new DirectoryResources(new File("src/mingle/java"), ResourcesTypes.JavaSource);
    assertThat(sources).hasSize(2);
    Resources classes = compiler.process(sources);
    assertThat(classes).hasSize(2);
  }

}
