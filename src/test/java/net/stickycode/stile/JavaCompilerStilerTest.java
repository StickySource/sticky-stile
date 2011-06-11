package net.stickycode.stile;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;

import net.stickycode.mockwire.Controlled;
import net.stickycode.mockwire.UnderTest;
import net.stickycode.mockwire.junit4.MockwireRunner;
import net.stickycode.resource.Resources;
import net.stickycode.resource.directory.DirectoryResources;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockwireRunner.class)
public class JavaCompilerStilerTest {

  @Controlled
  Workspace workspace;

  @UnderTest
  JavaCompilerStiler compiler;

  @Test
  public void compile() {
    when(workspace.getOutputDirectory()).thenReturn(new File("target/stile"));
    Resources sources = new DirectoryResources(new File("src/test/java"), ResourcesTypes.JavaSource);
    assertThat(sources).hasSize(4);
    Resources classes = compiler.process(sources);
    assertThat(classes).hasSize(6);
  }

}
