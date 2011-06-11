package net.stickycode.stile;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.stickycode.reflector.Reflector;
import net.stickycode.resource.CompositeResources;
import net.stickycode.resource.Resources;
import net.stickycode.resource.directory.DirectoryResources;

public class Stiler {

  private Logger log = LoggerFactory.getLogger(getClass());

  @Inject
  private ResourceListeners listeners;

  @Inject
  private ProcessesAnnotatedMethodProcessor processesAnnotatedMethodProcessor;

  public void register(Object plugin) {
    log.info("Registering plugin {}", plugin.getClass().getName());
    new Reflector()
        .forEachMethod(processesAnnotatedMethodProcessor)
        .process(plugin);
  }

  public void process(DirectoryResources directoryResources) {

  }

  public Resources produce(ResourcesTypes type) {
    log.info("Production of {} requested", type);
    CompositeResources resources = new CompositeResources();

    resources.add(transform(type));

    return produce(type, resources);
  }

  private Resources transform(ResourcesTypes type) {
    CompositeResources resources = new CompositeResources();
    for (Transformer transfomer: listeners.getTranformers(type)) {
      resources.add(transfomer.transform(produce(transfomer.getSourceType())));
    }
    return resources;
  }

  private Resources produce(ResourcesTypes type, CompositeResources resources) {
    for (Producer producer : listeners.getProducers(type)) {
      resources.add(producer.produce());
    }

    return process(type, resources);
  }

  private Resources process(ResourcesTypes type, CompositeResources input) {
    Resources resources = input;
    for (Processor processor : listeners.getProcessors(type)) {
      resources = processor.process(resources);
      if (resources == null)
        throw new NullPointerException();
    }
    return resources;
  }
}
