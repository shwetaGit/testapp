package com.app.shared.appinsight.alarms;
public class LoggingProperties {
	private String attributeComment;

	private String attributeName;

	private String attributeValue;

	private String attributeDisplayName;

	private String attributeId;

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(final String attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeComment() {
		return attributeComment;
	}

	public void setAttributeComment(final String attributeComment) {
		this.attributeComment = attributeComment;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(final String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(final String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getAttributeDisplayName() {
		return attributeDisplayName;
	}

	public void setAttributeDisplayName(final String attributeDisplayName) {
		this.attributeDisplayName = attributeDisplayName;
	}

	@Override
	public String toString() {
		return "LoggingProperties [attributeComment=" + attributeComment + ", attributeName=" + attributeName + ", attributeValue=" + attributeValue + ", attributeDisplayName="
				+ attributeDisplayName + ", attributeId=" + attributeId + "]";
	}
}
