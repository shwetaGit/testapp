package com.app.shared.appinsight.alarms;
public class LogCommons {

	private String attributeValue;

	private Integer attributeId;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(final String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public Integer getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(final Integer attributeId) {
		this.attributeId = attributeId;
	}

	@Override
	public String toString() {
		return "LogCommons [attributeValue=" + attributeValue + ", attributeId=" + attributeId + ", id=" + id + "]";
	}

}
