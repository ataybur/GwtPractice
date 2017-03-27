package org.gwtproject.tutorial.server.utils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

public class UploadServletImpl extends UploadAction {

    private static final long serialVersionUID = 3902980116489093700L;

    public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
	String response = null;
	Iterator<FileItem> fileItemsIterator = sessionFiles.iterator();
	while (fileItemsIterator.hasNext()) {
	    try {
		FileItem fileItem = fileItemsIterator.next();
		System.out.println("FieldName=" + fileItem.getFieldName());
		System.out.println("FileName=" + fileItem.getName());
		System.out.println("ContentType=" + fileItem.getContentType());
		System.out.println("Size in bytes=" + fileItem.getSize());
		// String tmpFile =
		// request.getServletContext().getAttribute("javax.servlet.context.temp‌​dir").toString();
		String tmpFile = "c://temp";
		String filePath = tmpFile + File.separator + fileItem.getName();
		File file = new File(tmpFile, fileItem.getName());
		System.out.println("Absolute Path at server=" + file.getAbsolutePath());
		fileItem.write(file);
		response = filePath;
	    } catch (FileUploadException e) {
		e.printStackTrace();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	// for (FileItem item : sessionFiles) {
	// if (!item.isFormField()) {
	// try {
	//
	// // we can save the received file
	// String fileName =
	// item.getName().substring(item.getName().lastIndexOf(File.separator) +
	// 1);
	// File file = new File(".");
	// //
	// // if (!file.exists())
	// // file.mkdirs();
	// // /*
	// // *
	// // * File file = File.createTempFile("receivedFile", ".tmp",
	// // * new File("./www/"));
	// // */
	// // File file = File.createTempFile("receivedFile",
	// // ".tmp",new File("./www/"));
	// // File file = new File("c://" + fileName);
	// item.write(file);
	//
	// response = fileName;
	// } catch (Exception e) {
	// throw new UploadActionException(e.getMessage());
	// }
	// }
	// }
	return response;
    }
}
