package net.stickycode.stile;

import net.stickycode.mockwire.Controlled;
import net.stickycode.mockwire.UnderTest;
import net.stickycode.mockwire.junit4.MockwireRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockwireRunner.class)
public class JavaSourceStilerTest {

  @UnderTest
  JavaSourcesStiler stiler;

  @Controlled
  Workspace workspace;

  @Test
  public void sources() {
System.out.println("asdfas");
  }
}
