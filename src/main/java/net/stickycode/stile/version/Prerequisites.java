package net.stickycode.stile.version;

public abstract class Prerequisites {

  public static <T> T notNull(T i, String message) {
    if (i == null)
      throw new NullPointerException(message);

    return i;
  }

  public static String notBlank(String i, String message) {
    return length(message, notNull(i, message), 0);
  }

  public static String length(String message, String i, int length) {
    if (i.trim().length() == length)
      throw new IllegalArgumentException(message);

    return i;
  }

}
