package com.app.server.businessservice.appinsight.alarms;
import com.app.shared.appinsight.alarms.ArtDomain;

import org.springframework.http.HttpEntity;

import com.athena.server.pluggable.utils.bean.ResponseBean;

public abstract class ArtLogAlarmService {

	public abstract HttpEntity<ResponseBean> getLogConfigDetails();

	public abstract HttpEntity<ResponseBean> updateLogAlarm();

	public abstract HttpEntity<ResponseBean> getListOfAlarms();

	public abstract HttpEntity<ResponseBean> getGridData(String domainId);
}
