package org.gwtproject.tutorial.server.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.gwtproject.tutorial.server.constants.Constants;
import org.gwtproject.tutorial.server.constants.MessageConstants;

public class ErrorWriterToFile extends WriterToFile {
	private Exception exception;

	public ErrorWriterToFile() {
		super();
	}

	public ErrorWriterToFile setException(Exception exp) {
		this.exception = exp;
		return this;
	}

	public ErrorWriterToFile prepareFile() {
		String formattedMessage = String.format(MessageConstants.MESSAGE_9, fileName);
		System.err.println(formattedMessage);
		messages = new ArrayList<String>();
		messages.add(Constants.ERROR);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		messages.add(sw.toString());
		return this;
	}
}
