package org.gwtproject.tutorial.server.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024
		* 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUpload extends HttpServlet {

	private static final long serialVersionUID = -7568304439685161405L;

	private ServletFileUpload uploader = null;

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	// public void doPost(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	//
	// response.setContentType("text/html;charset=UTF-8");
	//
	// // Create path components to save the file
	// final String path = request.getParameter("destination");
	// final Part filePart = request.getPart("file");
	// final String fileName = getFileName(filePart);
	//
	// OutputStream out = null;
	// InputStream filecontent = null;
	// final PrintWriter writer = response.getWriter();
	//
	// try {
	// out = new FileOutputStream(new File(path + File.separator + fileName));
	// filecontent = filePart.getInputStream();
	//
	// int read = 0;
	// final byte[] bytes = new byte[1024];
	//
	// while ((read = filecontent.read(bytes)) != -1) {
	// out.write(bytes, 0, read);
	// }
	// writer.println("New file " + fileName + " created at " + path);
	// } catch (FileNotFoundException fne) {
	// writer.println("You either did not specify a file to upload or are "
	// + "trying to upload a file to a protected or nonexistent " +
	// "location.");
	// writer.println("<br/> ERROR: " + fne.getMessage());
	//
	// } finally {
	// if (out != null) {
	// out.close();
	// }
	// if (filecontent != null) {
	// filecontent.close();
	// }
	// if (writer != null) {
	// writer.close();
	// }
	// }
	// }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException("Content type is not multipart/form-data");
		}
		 Enumeration<String> enumeration = request.getParameterNames();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");
		try {
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while (fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();
				System.out.println("FieldName=" + fileItem.getFieldName());
				System.out.println("FileName=" + fileItem.getName());
				System.out.println("ContentType=" + fileItem.getContentType());
				System.out.println("Size in bytes=" + fileItem.getSize());

				File file = new File(
						request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileItem.getName());
				System.out.println("Absolute Path at server=" + file.getAbsolutePath());
				fileItem.write(file);
				out.write("File " + fileItem.getName() + " uploaded successfully.");
				out.write("<br>");
				out.write("<a href=\"UploadDownloadFileServlet?fileName=" + fileItem.getName() + "\">Download "
						+ fileItem.getName() + "</a>");
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			out.write("Exception in uploading file.");
		} catch (Exception e) {
			e.printStackTrace();
			out.write("Exception in uploading file.");
		}
		out.write("</body></html>");
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
