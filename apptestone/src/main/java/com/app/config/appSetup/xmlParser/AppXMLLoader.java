package com.app.config.appSetup.xmlParser;
import com.app.config.appSetup.model.AppConfiguration;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

public class AppXMLLoader {
	private AppSetupSAXParser appSetupSAXParser;
	private AppConfiguration appConfiguration;

	@Autowired
	private ResourceLoader resourceLoader;

	public AppXMLLoader() {
		super();
	}

	public AppConfiguration getAppConfiguration() {
		return appConfiguration;
	}

	private void readAppSetupFile(File inputStream) {
		try {
			if (appSetupSAXParser == null) {
				appSetupSAXParser = new AppSetupSAXParser();
			}
			this.appConfiguration = (AppConfiguration) appSetupSAXParser.parse(inputStream);
		} catch (SAXParserException e) {
			e.printStackTrace();
		}
	}

	public void loadAppProperties(File appSetup1) throws IOException {

		try {
			readAppSetupFile(appSetup1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
