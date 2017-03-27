package org.gwtproject.tutorial.server.lambda;

import java.util.function.Supplier;

@FunctionalInterface
public interface SupplierThrowing<T> extends Supplier<T> {

	@Override
	default T get() {
		try {
			return getThrowing();
		} catch (Exception e) {
			throwAsUnchecked(e);
		}
		return null;
	}

	T getThrowing() throws Exception;

	@SuppressWarnings("unchecked")
	static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
		throw (E) exception;
	}
}
