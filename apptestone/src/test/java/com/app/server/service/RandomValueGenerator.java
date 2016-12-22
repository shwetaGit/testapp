package com.app.server.service;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;

public class RandomValueGenerator {
	final String ALL_CHARS_NUMBERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public String randomValueGenerate(String dataType, Integer maxValue, Integer minValue) {
		StringBuilder sb = new StringBuilder();
		if (dataType.equalsIgnoreCase("varchar") || dataType.equalsIgnoreCase("String") || dataType.equalsIgnoreCase("text")) {

			if (minValue != null && maxValue != null) {
				int length = maxValue >= 50 ? 50 : maxValue;
				return sb.append("\"" + randomString(length) + "\"").toString();

			} else if (minValue != null && maxValue == null && minValue > 0) {
				int length = minValue >= 50 ? 50 : minValue;

				return sb.append("\"" + randomString(length) + "\"").toString();

			} else if (maxValue != null && minValue == null) {
				int length = maxValue >= 50 ? 50 : maxValue;
				return sb.append("\"" + randomString(length) + "\"").toString();
			} else {
				return sb.append("\"" + randomString(3) + "\"").toString();
			}
		}

		if (dataType.equalsIgnoreCase("date")) {
			return sb.append("new java.sql.Date(System.currentTimeMillis())").toString();
		}
		if (dataType.equalsIgnoreCase("long") || dataType.equalsIgnoreCase("BIGINT")) {
			return sb.append("1234l").toString();
		}

		if (dataType.equalsIgnoreCase("double")) {
			return sb.append("12.34").toString();
		}
		if (dataType.equalsIgnoreCase("datetime")) {
			return sb.append("new java.sql.Timestamp(System.currentTimeMillis())").toString();
		}
		if (dataType.equalsIgnoreCase("timestamp")) {
			return sb.append("new java.sql.Timestamp(System.currentTimeMillis())").toString();
		}
		if (dataType.equalsIgnoreCase("tinyint")) {
			return sb.append("true").toString();
		}
		if (dataType.equalsIgnoreCase("boolean")) {
			return sb.append("true").toString();
		}
		return sb.toString();
	}

	String randomString(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(ALL_CHARS_NUMBERS.charAt(rnd.nextInt(ALL_CHARS_NUMBERS.length())));
		return sb.toString();
	}

	public Float getRandomFloat(Float maxValue, Float minValue) {
		Random random = new Random();
		if (minValue != null && maxValue != null) {
			return (random.nextFloat() * maxValue) + minValue;

		} else if (minValue != null && maxValue == null && minValue > 0) {
			return (random.nextFloat() * minValue);

		} else if (maxValue != null && minValue == null) {

			return (random.nextFloat() * maxValue);
		} else {
			return random.nextFloat();
		}
	}

	public Double getRandomDouble(Double maxValue, Double minValue) {
		Random random = new Random();
		if (minValue != null && maxValue != null) {
			return (random.nextDouble() * maxValue) + minValue;

		} else if (minValue != null && maxValue == null && minValue > 0) {
			return (random.nextDouble() * minValue);

		} else if (maxValue != null && minValue == null) {

			return (random.nextDouble() * maxValue);
		} else {
			return random.nextDouble();
		}

	}

	public Integer getRandomInteger(Integer maxValue, Integer minValue) {
		Random ran = new Random();
		if (minValue != null && maxValue != null) {

			int max = maxValue;
			int min = minValue;
			return ran.nextInt(max) + min;

		} else if (minValue != null && maxValue != null) {

			int min = minValue;
			return ran.nextInt(min) + min;
		} else if (maxValue != null && minValue != null) {

			int max = maxValue;
			return ran.nextInt(max) + max;
		}
		return ran.nextInt();
	}

	public Long getRandomLong(Long maxValue, Long minValue) {
		Random ran = new Random();
		if (minValue != null && maxValue != null) {

			return (long) ((ran.nextDouble() * maxValue) + minValue);

		} else if (minValue != null && maxValue != null) {

			return (long) (ran.nextDouble() * minValue);
		} else if (maxValue != null && minValue != null) {

			return (long) (ran.nextDouble() * maxValue);
		}
		return ran.nextLong();
	}

	public Date getDate() {
		return new Date(System.currentTimeMillis());
	}

	public Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public BigDecimal getRandomBigDecimal(Double maxValue, Double minValue) {
		Random random = new Random();
		if (minValue != null && maxValue != null) {
			return new BigDecimal((random.nextDouble() * maxValue) + minValue);

		} else if (minValue != null && maxValue == null && minValue > 0) {
			return new BigDecimal((random.nextDouble() * minValue));

		} else if (maxValue != null && minValue == null) {

			return new BigDecimal((random.nextDouble() * maxValue));
		} else {
			return new BigDecimal(random.nextDouble());
		}

	}

}
