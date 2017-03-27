package org.gwtproject.tutorial.server.lambda;

@FunctionalInterface
public interface RunnableThrowing extends Runnable {

	@Override
	default void run() {
		try {
			runThrowing();
		} catch (Exception e) {
			throwAsUnchecked(e);
		}
	}

	void runThrowing() throws Exception;

	@SuppressWarnings("unchecked")
	static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
		throw (E) exception;
	}
}
