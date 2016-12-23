package com.app.config.appSetup.xmlParser;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * All XML SAX Parser MUST extend from this abstract class.
 * 
 * <pre>
 * 
 * public class MySAXParser extends AbstractSAXParser {
 * 
 * 	private MyCustomObject myCustomerObject;
 * 
 * 	public MySAXParser() {
 * 		myCustomObject = new MyCustomObject();
 * 	}
 * 
 * 	public Object getObject() {
 * 		return myCustomObject;
 * 	}
 * 
 * 	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
 * 
 * 		// reset
 * 		resetTagData();
 * 
 * 		// Process the Start Element Tags
 * 	}
 * 
 * 	public void endElement(String uri, String localName, String qName) throws SAXException {
 * 
 * 		String dataValue = getTagData();
 * 
 * 		// Process the end elements
 * 	}
 * 
 * 	// ------------------------------------------------------------------------------
 * 	// For Testing the SAX Parser - DON'T USE main() INSIDE APPLICATION
 * 	// ------------------------------------------------------------------------------
 * 
 * 	public static void main(String[] args) throws SAXParserException {
 * 
 * 		MyCustomObject mco = null;
 * 		MySAXParser parser = new MySAXParser();
 * 
 * 		// Parse XML String
 * 		mco = (MyCustomObject) parser.parse(&quot;XML String&quot;);
 * 
 * 		// OR Parse XML from an InputStream (File / Socket)
 * 
 * 		mco = (MyCustomObject) parser.parse(InputStream);
 * 
 * 		// OR Parse XML from a File
 * 
 * 		mco = (MyCustomObject) parser.parse(new File(&quot;/usr/home/data/xmlfile.xml&quot;));
 * 
 * 		// OR Parse XML from a File
 * 
 * 		String fileName = System.getProperties(&quot;fileName&quot;);
 * 
 * 		mco = (MyCustomObject) parser.parseFile(fileName);
 * 	}
 * 
 * }
 * </pre>
 * 
 */

public abstract class AbstractSAXParser extends DefaultHandler {

	private CharArrayWriter charArrayWriter = new CharArrayWriter();

	/**
	 * This method returns a Custom POJO representing the parsed XML. This
	 * method needs to be over written in the child class with a statement to
	 * return the custom object.
	 * 
	 * <pre>
	 * public Object getObject() {
	 * 	return myCustomObject;
	 * }
	 * </pre>
	 * 
	 * @return Object
	 */
	public abstract Object getObject();

	/**
	 * Read the values from the stream
	 */

	public final void characters(char[] ch, int start, int length) throws SAXException {
		charArrayWriter.write(ch, start, length);
	}

	/**
	 * Resets the Character streams. This method MUST be invoked as the first
	 * instruction inside the startElement() method call.
	 */
	public final void resetTagData() {
		charArrayWriter.reset();
	}

	/**
	 * This method returns the contents of an XML tag. This method MUST be
	 * invoked as the first line inside the endElement() method call.
	 * 
	 * @return String
	 */
	public String getTagData() {
		return charArrayWriter.toString();
	}

	/**
	 * Parse the XML String and return the object (POJO) model
	 * 
	 * @params String
	 * @return Object
	 * @throws SAXParserException
	 */

	public final Object parse(String _xml) throws SAXParserException {
		if (_xml == null) {
			throw new SAXParserException("Invalid (NULL) XML");
		}
		parse(new ByteArrayInputStream(_xml.getBytes()));
		return getObject();
	}

	/**
	 * Parse the XML from an Input Stream (File / Socket) and return the object
	 * (POJO) model
	 * 
	 * @params String
	 * @return Object
	 * @throws SAXParserException
	 */

	public final Object parse(InputStream _stream) throws SAXParserException {
		if (_stream == null) {
			throw new SAXParserException("Invalid (NULL) XML Stream");
		}
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			SAXParser sp = spf.newSAXParser(); // Get new parser instance
			sp.parse(new BufferedInputStream(_stream), this); // Parse the XML
		} catch (SAXException se) {
			throw new SAXParserException("SAX Exception", se);
		} catch (ParserConfigurationException pce) {
			throw new SAXParserException("Parsing Exception", pce);
		} catch (IOException ie) {
			throw new SAXParserException("File I/O Exception", ie);
		}
		return getObject();
	}

	/**
	 * Parse the XML from a File and return the object (POJO) model
	 * 
	 * @params String FileName
	 * @return Object
	 * @throws SAXParserException
	 */
	public final Object parse(File _fileName) throws SAXParserException {
		if (_fileName == null) {
			throw new SAXParserException("Invalid file=[" + _fileName + "]");
		}
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			SAXParser sp = spf.newSAXParser(); // Get new parser instance
			sp.parse(_fileName, this); // Parse the XML
		} catch (SAXException se) {
			throw new SAXParserException("SAX Exception", se);
		} catch (ParserConfigurationException pce) {
			throw new SAXParserException("Parsing Exception", pce);
		} catch (IOException ie) {
			throw new SAXParserException("File I/O Exception", ie);
		}
		return getObject();
	}

	/**
	 * Parse the XML from a File and return the object (POJO) model
	 * 
	 * @params String FileName
	 * @return Object
	 * @throws SAXParserException
	 */
	public final Object parseFile(String fileName) throws SAXParserException {
		if (fileName == null) {
			throw new SAXParserException("Invalid file=[" + fileName + "]");
		}
		return parse(new File(fileName));
	}
}
