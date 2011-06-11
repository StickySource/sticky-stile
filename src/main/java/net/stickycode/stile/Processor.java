package net.stickycode.stile;

import static net.stickycode.exception.resolver.ParameterResolver.resolve;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.stickycode.resource.Resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Processor {

  private Logger log = LoggerFactory.getLogger(getClass());

  private ResourcesTypes value;
  private Object target;
  private Method method;

  public Processor(ResourcesTypes value, Object target, Method method) {
    this.value = value;
    this.target = target;
    this.method = method;
  }

  public Resources process(Resources resources) {
    log.debug("{} with input {}", this, resources);
    try {
      return (Resources) method.invoke(target, new Object[] {resources});
    }
    catch (IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
    catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean processes(ResourcesTypes type) {
    return value.equals(type);
  }

  @Override
  public String toString() {
    return resolve("{}.{} processes {}",
        target.getClass().getSimpleName(), method.getName(), value);
  }

}
