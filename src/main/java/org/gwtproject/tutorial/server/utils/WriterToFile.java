package org.gwtproject.tutorial.server.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class WriterToFile {
	protected List<String> messages;
	protected String fileName;

	public WriterToFile() {
		super();
	}

	public WriterToFile(List<String> messages, String fileName) {
		this.messages = messages;
		this.fileName = fileName;
	}

	public WriterToFile setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public WriterToFile write() throws IOException {
		StringBuffer sb = new StringBuffer();
		messages.forEach((line) -> sb //
				.append(line) //
				.append(System.lineSeparator()) //
		);
		Path path = Paths.get(fileName);
		BufferedWriter writer = Files.newBufferedWriter(path);
		writer.write(sb.toString());
		writer.flush();
		return this;
	}

	public abstract WriterToFile prepareFile();

}
