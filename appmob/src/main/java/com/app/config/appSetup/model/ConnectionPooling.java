package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.ConnectionPoolingInterface;

public final class ConnectionPooling implements ConnectionPoolingInterface {

	private final int initialSize;
	private final int maxActive;
	private final int maxIdle;
	private final int minIdle;
	private final boolean testOnBorrow;
	private final boolean testOnReturn;
	private final boolean testWhileIdle;
	private final String validationQuery;
	private final int timeBetweenEvictionRunsMillis;
	private final int numTestPerEvictionRun;

	public ConnectionPooling(int initialSize, int maxActive, int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, boolean testWhileIdle, String validationQuery,
			int timeBetweenEvictionRunsMillis, int numTestPerEvictionRun) {
		this.initialSize = initialSize;
		this.maxActive = maxActive;
		this.maxIdle = maxIdle;
		this.minIdle = minIdle;
		this.testOnBorrow = testOnBorrow;
		this.testOnReturn = testOnReturn;
		this.testWhileIdle = testWhileIdle;
		this.validationQuery = validationQuery;
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
		this.numTestPerEvictionRun = numTestPerEvictionRun;
	}

	@Override
	public int getInitialSize() {
		return initialSize;
	}

	@Override
	public int getMaxActive() {
		return maxActive;
	}

	@Override
	public int getMaxIdle() {
		return maxIdle;
	}

	@Override
	public int getMinIdle() {
		return minIdle;
	}

	@Override
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	@Override
	public boolean isTestOnRun() {
		return testOnReturn;
	}

	@Override
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	@Override
	public String getValidationQuery() {
		return validationQuery;
	}

	@Override
	public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	@Override
	public int getNumTestPerEvictionRun() {
		return numTestPerEvictionRun;
	}

	@Override
	public String toString() {
		return "ConnectionPooling [initialSize=" + initialSize + ", maxActive=" + maxActive + ", maxIdle=" + maxIdle + ", minIdle=" + minIdle + ", testOnBorrow=" + testOnBorrow
				+ ", testOnRun=" + testOnReturn + ", testWhileIdle=" + testWhileIdle + ", validationQuery=" + validationQuery + ", timeBetweenEvictionRunsMillis="
				+ timeBetweenEvictionRunsMillis + ", numTestPerEvictionRun=" + numTestPerEvictionRun + "]";
	}
}
