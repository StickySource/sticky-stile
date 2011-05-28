package net.stickycode.stile.version;

import static net.stickycode.stile.version.Prerequisites.notBlank;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;


public class VersionParser {

  private static Pattern NUMERIC_VERSION = Pattern.compile("[pr]?[0-9]+");

  public Version parse(String versionString) {
    return process(notBlank(versionString, "Version spec cannot be blank"));
  }

  private Version process(String versionString) {
    List<VersionComponent<?>> v = new LinkedList<VersionComponent<?>>();

    for (String s : versionString.split("[-,_:.]")) {
      if (NUMERIC_VERSION.matcher(s).matches())
        v.add(parseNumeric(s));

      else
        v.add(parseString(s));
    }

    return new Version(v);
  }

  private VersionComponent<?> parseString(String s) {
    try {
      return new DefinedStringVersionComponent(VersionDefinition.fromCode(s));
    }
    catch (IllegalArgumentException e) {
      return new StringVersionComponent(s);
    }
  }

  private VersionComponent<?> parseNumeric(String s) {
    if (s.startsWith("p"))
      return new PatchNumericVersionComponent(s.substring(1));

    if (s.startsWith("r"))
      return new RevisionNumericVersionComponent(s.substring(1));

    return new NumericVersionComponent(new Integer(s));
  }

}
