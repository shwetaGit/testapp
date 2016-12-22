package com.app.bean;
/**
 * CLASS IS USED TO CONTAIN THE DOCUMENT INFORMATION AFTER ITS BEEN UPLOADED
 * 
 * DOCUMENT INFO 1. DOCUMENT NAME 2. DOCUMENT PATH
 * 
 * */
public class DocumentBean {

	private String documentName;

	private String documentPath;

	public DocumentBean() {
		super();
	}

	public DocumentBean(final String documentName, final String documentPath) {
		super();
		this.documentName = documentName;
		this.documentPath = documentPath;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(final String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(final String documentPath) {
		this.documentPath = documentPath;
	}

	@Override
	public String toString() {
		return "DocumentBean [documentName=" + documentName + ", documentPath=" + documentPath + "]";
	}

}
