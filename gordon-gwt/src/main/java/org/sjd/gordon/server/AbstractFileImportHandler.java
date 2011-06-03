package org.sjd.gordon.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.sjd.gordon.importing.ImportException;

@SuppressWarnings("serial")
public abstract class AbstractFileImportHandler extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<FileItem> items = getFileItems(request);
        FileItem uploadItem = getUploadedFile(items);
        if (uploadItem == null) {
            response.getWriter().write("NO_DATA_FOUND");
            return;
        } else {
        	InputStreamReader reader = new InputStreamReader(uploadItem.getInputStream());
        	try {
				importFile(getParameters(items), reader);
			} catch (ImportException e) {
				response.getWriter().write("FAILED");
			}
        }
        response.getWriter().write("SUCCESS");
    }
	
	protected abstract void importFile(Map<String,String> parameters, Reader reader) throws ImportException;
	
	@SuppressWarnings("unchecked")
	private List<FileItem> getFileItems(HttpServletRequest request) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        try {
            return (List<FileItem>)upload.parseRequest(request);
        }
        catch(FileUploadException e){
            return null;
        }
    }
	
	private FileItem getUploadedFile(List<FileItem> items) {
		Iterator<FileItem> it = items.iterator();
        while(it.hasNext()) {
            FileItem item =  it.next();
            if(!item.isFormField() && "uploadFormElement".equals(item.getFieldName())) {
                return item;
            } 
        }
        return null;
	}

	private Map<String,String> getParameters(List<FileItem> items) {
		HashMap<String,String> parameters = new HashMap<String,String>();
		Iterator<FileItem> it = items.iterator();
        while(it.hasNext()) {
            FileItem item =  it.next();
            if (item.isFormField()) {
                parameters.put(item.getFieldName(),item.getString());
            } 
        }
        return parameters;
	}
}
