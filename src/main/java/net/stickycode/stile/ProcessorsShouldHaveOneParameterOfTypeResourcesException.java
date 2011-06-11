package net.stickycode.stile;

import net.stickycode.exception.PermanentException;
import net.stickycode.resource.Resources;


@SuppressWarnings("serial")
public class ProcessorsShouldHaveOneParameterOfTypeResourcesException
    extends PermanentException {

  public ProcessorsShouldHaveOneParameterOfTypeResourcesException(Class<?>[] parameterTypes) {
    super("Processors should have one parameter of type {} but found {}", Resources.class.getName(), parameterTypes);
  }

}
