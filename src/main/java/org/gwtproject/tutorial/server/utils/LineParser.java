package org.gwtproject.tutorial.server.utils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gwtproject.tutorial.server.enums.LineTypes;

public class LineParser {
	private Optional<LineTypes> lineTypes;
	private String line;

	public LineParser(Optional<LineTypes> lineTypes, String line) {
		super();
		this.lineTypes = lineTypes;
		this.line = line;
	}

	public LineInfo parseLineToInfo() {
		String[] result = null;
		if (lineTypes.isPresent()) {
			Matcher matcher = Pattern //
					.compile(lineTypes.get().getRegex()) //
					.matcher(line);
			if (matcher.find()) {
				int size = matcher.groupCount();
				result = new String[size];
				for (int i = 1; i <= size; i++) {
					result[i - 1] = matcher.group(i);
				}
			}
		}
		return new LineInfo(result, lineTypes);
	}
}
