package net.stickycode.stile;

import java.util.ArrayList;
import java.util.List;

public class ResourceListeners {

  private List<Producer> producers = new ArrayList<Producer>();
  private List<Processor> processors = new ArrayList<Processor>();
  private List<Transformer> transformers = new ArrayList<Transformer>();

  public void addConsumer(Consumer consumer) {
  }

  public void addProcessor(Processor processor) {
    this.processors.add(processor);
  }

  public void addTranformer(Transformer transformer) {
    transformers.add(transformer);
  }

  public Iterable<Producer> getProducers(final ResourcesTypes type) {
    return new FilteringIterator<Producer>(producers.iterator()) {

      @Override
      protected boolean include(Producer next) {
        return next.produces(type);
      }
    };
  }

  public Iterable<Processor> getProcessors(final ResourcesTypes type) {
    return new FilteringIterator<Processor>(processors.iterator()) {

      @Override
      protected boolean include(Processor next) {
        return next.processes(type);
      }
    };
  }

  public void addProducer(Producer producer) {
    producers.add(producer);
  }

  public Iterable<Transformer> getTranformers(final ResourcesTypes type) {
    return new FilteringIterator<Transformer>(transformers.iterator()) {

      @Override
      protected boolean include(Transformer next) {
        return next.produces(type);
      }
    };
  }
}
