package net.stickycode.stile;

import static net.stickycode.exception.resolver.ParameterResolver.resolve;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.stickycode.resource.Resources;

public class Producer {

  private Logger log = LoggerFactory.getLogger(getClass());

  private ResourcesTypes value;

  private Object target;

  private Method method;

  public Producer(ResourcesTypes value, Object target, Method method) {
    this.value = value;
    this.target = target;
    this.method = method;
  }

  public Resources produce() {
    log.debug("called {}", this);
    try {
      return (Resources) method.invoke(target, new Object[0]);
    }
    catch (IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
    catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    catch (InvocationTargetException e) {
      if (e.getCause() instanceof RuntimeException)
        throw (RuntimeException)e.getCause();

      throw new RuntimeException(e);
    }
  }

  public boolean produces(ResourcesTypes type) {
    return value.equals(type);
  }

  @Override
  public String toString() {
    return resolve("{}.{} producing {}",
        target.getClass().getSimpleName(), method.getName(), value);
  }


}
