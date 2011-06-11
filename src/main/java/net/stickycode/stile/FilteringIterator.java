package net.stickycode.stile;

import java.util.Iterator;

public abstract class FilteringIterator<T>
    implements Iterator<T>, Iterable<T> {

  private Iterator<T> delegate;
  private T next;

  public FilteringIterator(Iterator<T> iterator) {
    this.delegate = iterator;
  }

  @Override
  public boolean hasNext() {
    while (delegate.hasNext()) {
      this.next = delegate.next();

      if (include(next))
        return true;
    }

    return false;
  }

  abstract protected boolean include(T next);

  @Override
  public T next() {
    return next;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Filtering iterators are immutable");
  }

  @Override
  public Iterator<T> iterator() {
    return this;
  }

}
