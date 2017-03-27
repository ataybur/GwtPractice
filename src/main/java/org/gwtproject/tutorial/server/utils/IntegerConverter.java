package org.gwtproject.tutorial.server.utils;

public class IntegerConverter {
	private String instance;

	public IntegerConverter(String instance) {
		this.instance = instance;
	}

	public Integer convert() {
		Integer result;
		try {
			result = Integer.valueOf(this.instance);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}
}
