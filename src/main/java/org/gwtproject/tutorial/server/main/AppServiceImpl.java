package org.gwtproject.tutorial.server.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.gwtproject.tutorial.client.AppService;
import org.gwtproject.tutorial.server.lambda.FunctionThrowing;
import org.gwtproject.tutorial.server.lambda.LineInfoCollector;
import org.gwtproject.tutorial.server.lambda.RunnableThrowing;
import org.gwtproject.tutorial.server.pojo.Context;
import org.gwtproject.tutorial.server.utils.ConsoleWriterToFile;
import org.gwtproject.tutorial.server.utils.ErrorWriterToFile;
import org.gwtproject.tutorial.server.utils.FileReader;
import org.gwtproject.tutorial.server.utils.Game;
import org.gwtproject.tutorial.server.utils.LineChecker;
import org.gwtproject.tutorial.server.utils.LineParser;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AppServiceImpl extends RemoteServiceServlet implements AppService {

    private static final long serialVersionUID = 906991527180188212L;

    private Optional<Context> context;
    
    private String result = "";

    public AppServiceImpl() {
	super();
    }

    public String load(String inputFile) {
	handleException(() -> {
	    // String fileNameInput = inputFile.getAbsolutePath();
	    // List<LineInfo> list =
	    context = new FileReader(inputFile) //
		    .getStream() //
		    .map(LineChecker::new) //
		    .map(LineChecker::parseForLineType) //
		    .map(LineParser::parseLineToInfo) //
		    .collect(new LineInfoCollector());
	});
	return context.map(Context::toString).orElse(result);
    }

    public String run() {
	context = Context.getInstance();
	FunctionThrowing<Game, Optional<Context>> gameStartGame = Game::startGame;
	context.map(Game::new) //
		.map(gameStartGame);
	return context.map(Context::getConsole).map(List::toString).orElse(result);
    }

    public String write(String outputFile) {
	handleException(() -> {
	    String fileNameOutput = outputFile + File.separatorChar + "output.txt";
	    new ConsoleWriterToFile(context) //
		    .setFileName(fileNameOutput) //
		    .prepareFile() //
		    .write();
	});
	return context.map(Context::toString).orElse(result);
    }

    private void handleException(RunnableThrowing runnable) {
	try {
	    runnable.run();
	} catch (Exception e) {
	    try {
		e.printStackTrace();
		new ErrorWriterToFile() //
			.setException(e) //
			.setFileName(new File("error.log").getAbsolutePath()) //
			.prepareFile() //
			.write();
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	}
    }
}
