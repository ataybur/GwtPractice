package org.gwtproject.tutorial.shared;

import java.io.Serializable;
import java.util.Date;

public final class FileDto implements Serializable {

    private static final long serialVersionUID = -3240678282015879129L;
    
    private String filename;
    private Date dateUploaded;

    public FileDto() {
    }

    public Date getDateUploaded() {
	return dateUploaded;
    }

    public void setDateUploaded(final Date dateUploaded) {
	this.dateUploaded = dateUploaded;
    }

    public String getFilename() {
	return filename;
    }

    public void setFilename(final String filename) {
	this.filename = filename;
    }
}
