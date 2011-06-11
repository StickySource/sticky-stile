package net.stickycode.stile;

import static net.stickycode.exception.resolver.ParameterResolver.resolve;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.stickycode.resource.Resources;

public class Transformer {

  private Logger log = LoggerFactory.getLogger(getClass());

  private final ResourcesTypes sourceType;

  private final ResourcesTypes target;

  private final Object plugin;

  private final Method transfomer;

  public Transformer(ResourcesTypes source, ResourcesTypes target, Object plugin, Method transfomer) {
    super();
    this.sourceType = source;
    this.target = target;
    this.plugin = plugin;
    this.transfomer = transfomer;
  }

  public boolean produces(ResourcesTypes type) {
    return target.equals(type);
  }

  public ResourcesTypes getSourceType() {
    return sourceType;
  }

  public Resources transform(Resources resources) {
    log.debug("called {}", this);
    try {
      return (Resources) transfomer.invoke(plugin, new Object[] {resources});
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

  @Override
  public String toString() {
    return resolve("{}.{} transforming {} to {}",
        plugin.getClass().getSimpleName(), transfomer.getName(), sourceType, target);
  }


}
