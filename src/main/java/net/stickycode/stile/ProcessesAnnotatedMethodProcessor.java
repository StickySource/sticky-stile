package net.stickycode.stile;

import java.lang.reflect.Method;

import javax.inject.Inject;

import net.stickycode.reflector.AnnotatedMethodProcessor;
import net.stickycode.resource.Resources;

public class ProcessesAnnotatedMethodProcessor
    extends AnnotatedMethodProcessor {

  @Inject
  private ResourceListeners listeners;

  public ProcessesAnnotatedMethodProcessor() {
    super(Processes.class, Produces.class);
  }

  @Override
  public boolean canProcess(Method method) {
    Produces generates = method.getAnnotation(Produces.class);
    if (generates != null)
      if (!Resources.class.isAssignableFrom(method.getReturnType()))
        throw new ProducesShouldReturnResourcesException(method.getReturnType());

    Processes processes = method.getAnnotation(Processes.class);
    if (processes != null)
      if (methodDoesNotHaveSingleParameterOfTypeResources(method))
        throw new ProcessorsShouldHaveOneParameterOfTypeResourcesException(method.getParameterTypes());

    return processes != null || generates != null;
  }

  private boolean methodDoesNotHaveSingleParameterOfTypeResources(Method method) {
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (parameterTypes.length != 1)
      return true;

    if (!Resources.class.isAssignableFrom(parameterTypes[0]))
      return true;

    return false;
  }

  @Override
  public void processMethod(Object target, Method method) {
    Produces generates = method.getAnnotation(Produces.class);
    Processes processes = method.getAnnotation(Processes.class);
    if (generates == null)
      listeners.addConsumer(new Consumer(processes.value(), target, method));

    else
      if (processes == null)
        listeners.addProducer(new Producer(generates.value(), target, method));

      else
        if (processes.value().equals(generates.value()))
          listeners.addProcessor(new Processor(processes.value(), target, method));

        else
          listeners.addTranformer(new Transformer(processes.value(), generates.value(), target, method));
  }

}
