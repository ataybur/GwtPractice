package org.gwtproject.tutorial.server.lambda;

import java.util.function.BiFunction;

@FunctionalInterface
public interface BiFunctionThrowing<T, U, R> extends BiFunction<T, U, R> {
	@Override
	default R apply(T t, U u) {
		try {
			return applyThrowing(t, u);
		} catch (Exception e) {
			throwAsUnchecked(e);
		}
		return null;
	}

	R applyThrowing(T t, U u) throws Exception;

	@SuppressWarnings("unchecked")
	static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
		throw (E) exception;
	}
}
