package com.app.config.appSetup.xmlParser;
public class SAXParserException extends Exception {

	/**
	 * Create the Exception with the Error Message
	 * 
	 * @param String
	 */

	public SAXParserException(String _mesg) {
		super(_mesg);
	}

	/**
	 * Create the Exception with the Error Message and a Throwable object
	 * 
	 * @param String
	 *            , Throwable
	 */

	public SAXParserException(String _mesg, Throwable _throw) {
		super(_mesg, _throw);
	}

}
