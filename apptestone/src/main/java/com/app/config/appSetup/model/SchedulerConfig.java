package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.SchedulerConfigInteface;

public class SchedulerConfig implements SchedulerConfigInteface {

	private String schedulerUrl;
	private int schedulerRefreshTime;

	@Override
	public String getSchedulerUrl() {
		return schedulerUrl;
	}

	public void setSchedulerUrl(String schedulerUrl) {
		this.schedulerUrl = schedulerUrl;
	}

	public SchedulerConfig(String schedulerUrl, int schedulerRefreshTime) {
		super();
		this.schedulerUrl = schedulerUrl;
	}

	public SchedulerConfig() {
		super();
	}

	@Override
	public int getSchedulerRefreshTime() {
		return schedulerRefreshTime;
	}

	public void setSchedulerRefreshTime(int schedulerRefreshTime) {
		this.schedulerRefreshTime = schedulerRefreshTime;
	}

}
