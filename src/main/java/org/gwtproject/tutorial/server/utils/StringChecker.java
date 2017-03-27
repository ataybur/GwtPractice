package org.gwtproject.tutorial.server.utils;

public class StringChecker {
	private String instance;

	public StringChecker(String instance) {
		this.instance = instance;
	}

	public boolean isEmpty() {
		return this.instance == null || this.instance.isEmpty();
	}

	public boolean isNotEmpty() {
		return !isEmpty();
	}
}
