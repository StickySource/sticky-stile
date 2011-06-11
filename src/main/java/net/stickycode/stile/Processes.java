package net.stickycode.stile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Processes {
  ResourcesTypes value();

  // TODO? could add optional class value that is used for extensions
}
