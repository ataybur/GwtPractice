package org.gwtproject.tutorial.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AppServiceAsync {

    void run(AsyncCallback<String> callback);

    void write(String outputFile, AsyncCallback<String> callback);

    void load(String inputFile, AsyncCallback<String> callback);

}
