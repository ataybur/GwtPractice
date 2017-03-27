package org.gwtproject.tutorial.server.utils;

public class PojoGetter<T> {
	private T t;
	private Class<? extends T> clazz;

	public PojoGetter(T t, Class<? extends T> clazz) {
		this.t = t;
		this.clazz = clazz;
	}

	public <T, E extends T> E get() throws InstantiationException, IllegalAccessException {
		if (t == null) {
			t = clazz.newInstance();
		}
		return (E) t;
	}
}
