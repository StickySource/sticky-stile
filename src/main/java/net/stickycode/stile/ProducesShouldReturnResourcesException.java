package net.stickycode.stile;

import javax.annotation.Resources;

import net.stickycode.exception.PermanentException;


@SuppressWarnings("serial")
public class ProducesShouldReturnResourcesException
    extends PermanentException {

  public ProducesShouldReturnResourcesException(Class<?> returnType) {
    super("Expected return type of {} but was {}", Resources.class.getName(), returnType.getName());
  }

}
