package net.stickycode.stile.version;

abstract class VersionComponent<T> {

	protected abstract T getValue();
  abstract public String toString();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + domainHashCode();
		return result;
	}

	protected int domainHashCode() {
		return getValue().hashCode();
	}

	@SuppressWarnings("unchecked")
  @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		return domainEquals((VersionComponent<T>)obj);
	}

	protected boolean domainEquals(VersionComponent<T> other) {
		return getValue().equals(other.getValue());
	}

}
