package net.stickycode.stile;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class WorkspaceTest {

  @Test
  public void register() {
//    Workspace workspace = new Workspace() {};
//    workspace.addArtifact(new Artifact("id", new ComponentVersionParser().parse("1.1")));
    // workspace.addPlugin(new JavaCompilerStiler());
  }

  @Test
  public void classMimeType() throws IOException {
    URL u = new URL("file://" + new File("target-eclipse/test-classes/net/stickycode/stile/WorkspaceTest.class").getAbsolutePath());
    // assertThat(u.toString()).isEqualTo("");
    URLConnection uc = u.openConnection();
    assertThat(uc.getContentType()).isEqualTo("application/java-vm");
  }

  @Test
  public void javaMimeType() throws IOException {
    URL u = new URL("file://" + new File("src/test/java/net/stickycode/stile/WorkspaceTest.java").getAbsolutePath());
    // assertThat(u.toString()).isEqualTo("");
    URLConnection uc = u.openConnection();
    assertThat(uc.getContentType()).isEqualTo("text/plain");
  }

}
