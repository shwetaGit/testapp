package com.app.util;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	/**
	 * METHOD IS USED TO CONVERT VALUE TO TIMESTAMP
	 * 
	 * @throws ParseException
	 */
	public Timestamp convertValueToTimeStamp(String timestamp) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		java.util.Date date = formatter.parse(timestamp);
		Timestamp timestampobj = new Timestamp(date.getTime());
		return timestampobj;
	}

	/**
	 * METHOD IS USED TO CONVERT VALUE TO DATE
	 * 
	 * @throws ParseException
	 */
	public Date convertValueToDate(String datevalue) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = formatter.parse(datevalue);
		return new Date(date.getTime());
	}
}
