package org.gwtproject.tutorial.server.lambda;

import java.util.function.Function;

@FunctionalInterface
public interface FunctionThrowing<T, R> extends Function<T, R> {
	@Override
	default R apply(T t) {
		try {
			return applyThrowing(t);
		} catch (Exception e) {
			throwAsUnchecked(e);
		}
		return null;
	}

	R applyThrowing(T t) throws Exception;

	@SuppressWarnings("unchecked")
	static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
		throw (E) exception;
	}
}
