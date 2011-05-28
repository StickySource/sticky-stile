package net.stickycode.stile.version;

import static org.fest.assertions.Assertions.assertThat;

import net.stickycode.stile.version.DefinedStringVersionComponent;
import net.stickycode.stile.version.NumericVersionComponent;
import net.stickycode.stile.version.PatchNumericVersionComponent;
import net.stickycode.stile.version.RevisionNumericVersionComponent;
import net.stickycode.stile.version.StringVersionComponent;
import net.stickycode.stile.version.Version;
import net.stickycode.stile.version.VersionComponent;
import net.stickycode.stile.version.VersionDefinition;
import net.stickycode.stile.version.VersionParser;

import org.junit.Test;

public class VersionParserTest {

  @Test(expected = NullPointerException.class)
  public void nullVersion() {
    new VersionParser().parse(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyVersion() {
    new VersionParser().parse("");
  }

  @Test
  public void numeric() {
    check("1", new NumericVersionComponent(1));
    check("2", new NumericVersionComponent(2));
    check("2.1", new NumericVersionComponent(2), new NumericVersionComponent(1));
    check("0.1", new NumericVersionComponent(0), new NumericVersionComponent(1));
    check("0-1", new NumericVersionComponent(0), new NumericVersionComponent(1));
  }

  @Test
  public void revisions() {
    check("r1", new RevisionNumericVersionComponent(1));
    check("r2", new RevisionNumericVersionComponent(2));
    check("r0-1", new RevisionNumericVersionComponent(0), new NumericVersionComponent(1));
  }

  @Test
  public void patches() {
    check("p1", new PatchNumericVersionComponent(1));
    check("p2", new PatchNumericVersionComponent(2));
    check("p10", new PatchNumericVersionComponent(10));
    check("p10-1", new PatchNumericVersionComponent(10), new NumericVersionComponent(1));
  }

  @Test
  public void singlePointStringVersion() {
    check("a", new StringVersionComponent("a"));
    check("b", new StringVersionComponent("b"));
    check("a-a", new StringVersionComponent("a"), new StringVersionComponent("a"));
  }

  @Test
  public void greekVersion() {
    check("alpha", new DefinedStringVersionComponent(VersionDefinition.ALPHA));
    check("alPha", new DefinedStringVersionComponent(VersionDefinition.ALPHA));
    check("ALPHA", new DefinedStringVersionComponent(VersionDefinition.ALPHA));
    check("1-ALPHA", new NumericVersionComponent(1), new DefinedStringVersionComponent(VersionDefinition.ALPHA));

    check("beta", new DefinedStringVersionComponent(VersionDefinition.BETA));
    check("Beta", new DefinedStringVersionComponent(VersionDefinition.BETA));
    check("BETA", new DefinedStringVersionComponent(VersionDefinition.BETA));

    check("fcs", new DefinedStringVersionComponent(VersionDefinition.FCS));

    check("rc", new DefinedStringVersionComponent(VersionDefinition.RC));
  }

  private <T> void check(String versionString, VersionComponent<T> versionComponent) {
    Version v = new VersionParser().parse(versionString);
    assertThat(v).hasSize(1);
    assertThat(v).containsOnly(versionComponent);
    assertThat(v).excludes(new NumericVersionComponent(212));
    assertThat(v).excludes(new NumericVersionComponent(3));
  }

  private void check(String versionString, VersionComponent<?> first, VersionComponent<?> second) {
    Version v = new VersionParser().parse(versionString);
    assertThat(v).hasSize(2);
    assertThat(v).containsOnly(first, second);
    assertThat(v).excludes(new NumericVersionComponent(212));
    assertThat(v).excludes(new NumericVersionComponent(3));
  }
}
