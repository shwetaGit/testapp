package com.app.bean;
import java.util.HashMap;
import java.util.Map;

/**
 * CLASS IS USED TO GENERATE TEMPLATE USING VELOCITY TEMPLATE LIBRARY AND WRAP
 * THE DYNAMIC INPUTS WITH KEYS AND USED IN DOCUMENT CREATION SERVICE
 */
public class DocumentTemplate {

	private String templateId;

	private final Map<String, Object> references = new HashMap<String, Object>();

	/**
	 * initialize the add reference with given configuration
	 * 
	 * @param : referenceKey, referenceValue
	 * @returns : void
	 */
	public void addReference(final String referenceKey, final Object referenceValue) {
		references.put(referenceKey, referenceValue);
	}

	/**
	 * initialize the add reference with given configuration
	 * 
	 * @param :referenceKey, referenceValue
	 * @returns : void
	 */
	public void addReference(final String referenceKeyValue) {
		final String[] reference = referenceKeyValue.split(",");
		final String referenceKey = reference[0];
		final String referenceValue = reference[1];
		references.put(referenceKey, referenceValue);
	}

	public Map<String, Object> getReferences() {
		return references;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(final String templateId) {
		this.templateId = templateId;
	}
}
