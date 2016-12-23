package com.app.server.businessservice.appinsight.alarms;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.spartan.pluggable.logger.alarms.ApplicationAlarmContext;
import com.spartan.pluggable.logger.api.AlarmConfigManager;
import com.spartan.pluggable.logger.api.AlarmManager;

@Service
public class AppLogAlarmGenerationBizServiceImpl implements AppLogAlarmGenerationBizService {

	@Autowired
	private ResourceLoader resourceLoader;

	final String FILE_NAME = "appAlarm.xml";

	@Override
	public void reGenerateAppAlarmXml(String alarmData) {
		try {
			File appAlarmXml = resourceLoader.getResource("WEB-INF/conf/ApplicationAlarms.xml").getFile();
			String filePath = appAlarmXml.getAbsoluteFile().getParentFile().getAbsolutePath();
			AlarmConfigManager alarmConfigMgr = new AlarmConfigManager(filePath);
			alarmConfigMgr.writeAppAlarmXML(alarmData);
			System.out.println("ApplicationAlarm file updated");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
