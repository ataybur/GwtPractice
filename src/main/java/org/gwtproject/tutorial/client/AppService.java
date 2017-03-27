package org.gwtproject.tutorial.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


//@RemoteServiceRelativePath("appService")
public interface AppService extends RemoteService {
    public String load(String inputFile);
    public String run();
    public String write(String outputFile);
}
