package org.gwtproject.tutorial.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnCancelUploaderHandler;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.SingleUploader;
import gwtupload.client.Utils;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TodoList implements EntryPoint {
    private AppServiceAsync appService;
	@Override
	public void onModuleLoad() {
	    
	    appService = GWT.create(AppService.class);
	    ServiceDefTarget endpoint = (ServiceDefTarget) appService;
	    String appServiceUrl = GWT.getModuleBaseURL() + "appService";
	    endpoint.setServiceEntryPoint(appServiceUrl);
		// Set the pop up window for the file upload.

		VerticalPanel panel = new VerticalPanel();
		List<String> fileList = new ArrayList<String>();
		SingleUploader uploader = new SingleUploader(FileInputType.BROWSER_INPUT);

		uploader.setAvoidRepeatFiles(true);
		// // Create button for submit
		Button run = new Button("Run");
		String url = GWT.getModuleBaseURL() + "fileupload2";
		uploader.setServletPath(url);
		/*
		 * This will be called on the completion of one upload file The response
		 * was in the XML format which will like
		 */
		
		uploader.addOnFinishUploadHandler(new OnFinishUploaderHandler() {
			public void onFinish(IUploader uploader) {

				if (uploader.getStatus() == Status.SUCCESS) {

					String response = uploader.getServerResponse();
					System.out.println(response);
					if (response != null) {
						Document doc = XMLParser.parse(response);
						String message = Utils.getXmlNodeValue(doc, "message");
						String finished = Utils.getXmlNodeValue(doc, "finished");
						appService.load(message, new AsyncCallback<String>() {
						    
						    @Override
						    public void onSuccess(String result) {
							Window.alert(result);
							
						    }
						    
						    @Override
						    public void onFailure(Throwable caught) {
							Window.alert("Basarisiz");
							
						    }
						});
						fileList.add(message);
						System.out.println(fileList.size());

					}

					// uploader.reset();
				}

			}
		});
		// this will be called when the cancel button will be clicked
		uploader.addOnCancelUploadHandler(new OnCancelUploaderHandler() {
			public void onCancel(IUploader uploader) {
				String response = uploader.getServerResponse();
				System.out.println("-------------------cancel upload handler");

				Document doc = XMLParser.parse(response);
				String message = Utils.getXmlNodeValue(doc, "message");
				fileList.remove(message);
				System.out.println(fileList.size());
				System.out.println(response);
				// fileList.remove("");

			}
		});
		
		run.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

			    appService.run(new AsyncCallback<String>() {
			        
			        @Override
			        public void onSuccess(String result) {
			            Window.alert(result);
			    	
			        }
			        
			        @Override
			        public void onFailure(Throwable caught) {
			            Window.alert("Basarisiz");
			    	
			        }
			    });
			}
		});
		
		panel.add(uploader);
		panel.add(run);
		
		// Add Vertical Panel to Root Panel
		RootPanel.get().add(panel);
	}

	public void onModuleLoad2() {

		// Create new Instance of vertical panel to align the widgets
		VerticalPanel panel = new VerticalPanel();
		final FormPanel form = new FormPanel();
		String action = GWT.getModuleBaseURL() + "fileupload";
		form.setAction(action);
		// set form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		form.setWidget(panel);

		// Add label
		panel.add(new HTML("<label>Choose file to Upload:</label>"));

		// Create new Instance of FileUpload
		final FileUpload fileUpload = new FileUpload();

		// // Create button for submit
		Button uploadButton = new Button("Upload");

		// Add ClickHandler to the button
		uploadButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// Get file name

				String filename = fileUpload.getFilename();

				// Check the length of the filename
				if (filename.length() != 0) {
					form.submit();
				} else
					Window.alert("No file choosen");
			}
		});

		// Add a 'submit' button.
		panel.add(new Button("Submit", new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.submit();
			}
		}));

		// Add an event handler to the form.
		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				// This event is fired just before the form is submitted. We can
				// take
				// this opportunity to perform validation.
				System.out.println(event);
			}
		});

		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				// When the form submission is successfully completed, this
				// event is fired. Assuming the service returned a response
				// of type text/html, we can get the result text here
				System.out.println(event);
				// Window.alert(event.getResults());
			}
		});

		// Add widgets to Vertical Panel
		panel.add(fileUpload);
		panel.add(uploadButton);

		// Add Vertical Panel to Root Panel
		RootPanel.get().add(form);
	}

}
