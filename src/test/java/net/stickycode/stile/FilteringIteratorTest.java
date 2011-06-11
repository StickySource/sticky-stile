package net.stickycode.stile;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class FilteringIteratorTest {

  private final class LessThanTenFilteringIterator
      extends FilteringIterator<Integer> {

    private LessThanTenFilteringIterator(Iterator<Integer> iterator) {
      super(iterator);
    }

    @Override
    protected boolean include(Integer next) {
      return next < 10;
    }
  }

  @Test
  public void empty() {
    assertThat(filter(Collections.<Integer>emptyList()))
        .isEmpty();
  }

  @Test
  public void one() {
    assertThat(filter(asList(9)))
        .containsOnly(9);
  }

  @Test
  public void oneFiltered() {
    assertThat(filter(asList(19)))
        .isEmpty();
  }

  @Test
  public void oneFilteredOfTwo() {
    assertThat(filter(asList(19, 9)))
        .containsOnly(9);
  }

  @Test
  public void twoFiltered() {
    assertThat(filter(Arrays.asList(11, 19)))
        .isEmpty();
  }

  @Test
  public void manyFiltered() {
    assertThat(filter(Arrays.asList(11, 19, 35, 12312, 1234131)))
        .isEmpty();
  }

  @Test
  public void manyUnFiltered() {
    assertThat(filter(Arrays.asList(1, 9, 3, 5, -12312, -1234131)))
        .containsOnly(1, 9, 3, 5, -12312, -1234131);
  }

  private Iterable<Integer> filter(List<Integer> emptyList) {
    return new LessThanTenFilteringIterator(emptyList.iterator());
  }
}
