package com.app.server.businessservice.appbasicsetup.aaa;
public enum DataType {

	NUMBER(1), DATE_TIME(2), STRING(3), BOOLEAN(4), JSON(5);

	private final int value;

	private DataType(final int value) {
		this.value = value;
	}
	public int getValue()
	{
	    return value;
	}
}


