package com.app.config.appSetup.model;
public final class JpaProperties {

	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public JpaProperties() {
		super();
	}

	public JpaProperties(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

}
