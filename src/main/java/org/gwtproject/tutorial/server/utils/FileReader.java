package org.gwtproject.tutorial.server.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
	private Stream<String> stream;

	public FileReader(String fileName) throws IOException {
		stream = Files.lines(Paths.get(fileName));
	}

	public Stream<String> getStream() {
		return stream;
	}
}
