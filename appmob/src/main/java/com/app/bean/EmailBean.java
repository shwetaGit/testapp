package com.app.bean;
import java.util.ArrayList;
import java.util.List;

/**
 * CLASS IS USED TO GENERATE EMAIL CONFIGURATION AND PASS IT AS AN INPUT TO
 * EMAIL SENDER SERVICE
 */
public class EmailBean {

	/**
	 * ATTACHMENT PATH TYPES 1 - ABSOLUTE PATH 2 - RELATIVE PATH TO
	 * BASE_FILE_PATH DEFINED IN appConfiguration.xml 3 - RELATIVE PATH TO
	 * WEB_APP_PATH DEFINED IN appConfiguration.xml
	 * */
	public static final Integer ABSOLUTE_PATH = 1;

	public static final Integer RELATIVE_PATH_TO_BASE_FILE_PATH = 2;

	public static final Integer RELATIVE_PATH_TO_WEB_APP_PATH = 3;

	private String emailSubject = "";

	private String emailBody = "";

	private List<String> recipients;

	private List<String> attachmentPath;

	private boolean compressedAttachment;

	private List<String> attachmentRelativePathToBaseFilePath;

	private List<String> attachmentRelativePathToWebAppPath;

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(final String _emailSubject) {
		this.emailSubject = _emailSubject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(final String _emailBody) {
		this.emailBody = _emailBody;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(final ArrayList<String> _recipients) {
		this.recipients = _recipients;
	}

	public List<String> getAttachmentPath() {
		return attachmentPath;
	}

	public boolean isCompressedAttachment() {
		return compressedAttachment;
	}

	public void setCompressedAttachment(final boolean compressedAttachment) {
		this.compressedAttachment = compressedAttachment;
	}

	public List<String> getAttachmentRelativePathToBaseFilePath() {
		return attachmentRelativePathToBaseFilePath;
	}

	public List<String> getAttachmentRelativePathToWebAppPath() {
		return attachmentRelativePathToWebAppPath;
	}

	public EmailBean addRecipient(final String recipient) {
		if (this.recipients == null) {
			this.recipients = new ArrayList<String>();
		}
		if (this.recipients != null) {
			this.recipients.add(recipient);
		}
		return this;
	}

	public EmailBean removeRecipient(final String recipient) {
		if (this.recipients != null) {
			this.recipients.remove(recipient);
		}
		return this;
	}

	public Integer sizeOfRecipients() {
		if (this.recipients != null) {
			return this.recipients.size();
		}
		return 0;
	}

	public EmailBean addAttachmentPath(final String attachmentPath) {
		if (this.attachmentPath == null) {
			this.attachmentPath = new ArrayList<String>();
		}
		this.attachmentPath.add(attachmentPath);
		return this;
	}

	public EmailBean removeAttachmentPath(final String attachmentPath) {
		if (this.attachmentPath == null) {
			this.attachmentPath = new ArrayList<String>();
		}
		this.attachmentPath.remove(attachmentPath);
		return this;
	}

	public Integer sizeOfAttachmentPath() {
		if (this.attachmentPath != null) {
			return this.attachmentPath.size();
		}
		return 0;
	}

	public EmailBean addAttachmentRelativePathToBaseFilePath(final String attachmentPath) {
		if (this.attachmentRelativePathToBaseFilePath == null) {
			this.attachmentRelativePathToBaseFilePath = new ArrayList<String>();
		}
		this.attachmentRelativePathToBaseFilePath.add(attachmentPath);
		return this;
	}

	public EmailBean removeAttachmentRelativePathToBaseFilePath(final String attachmentPath) {
		if (this.attachmentRelativePathToBaseFilePath == null) {
			this.attachmentRelativePathToBaseFilePath = new ArrayList<String>();
		}
		this.attachmentRelativePathToBaseFilePath.remove(attachmentPath);
		return this;
	}

	public Integer sizeOfAttachmentRelativePathToBaseFilePath() {
		if (this.attachmentRelativePathToBaseFilePath != null) {
			return this.attachmentRelativePathToBaseFilePath.size();
		}
		return 0;
	}

	public EmailBean addAttachmentRelativePathToWebAppPath(final String attachmentPath) {
		if (this.attachmentRelativePathToWebAppPath == null) {
			this.attachmentRelativePathToWebAppPath = new ArrayList<String>();
		}
		this.attachmentRelativePathToWebAppPath.add(attachmentPath);
		return this;
	}

	public EmailBean removeAttachmentRelativePathToWebAppPath(final String attachmentPath) {
		if (this.attachmentRelativePathToWebAppPath == null) {
			this.attachmentRelativePathToWebAppPath = new ArrayList<String>();
		}
		this.attachmentRelativePathToWebAppPath.remove(attachmentPath);
		return this;
	}

	public Integer sizeOfAttachmentRelativePathToWebAppPath() {
		if (this.attachmentRelativePathToWebAppPath != null) {
			return this.attachmentRelativePathToWebAppPath.size();
		}
		return 0;
	}
}
