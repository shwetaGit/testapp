package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.SearchEngineConfigInterface;

public class SearchEngineConfig implements SearchEngineConfigInterface {

	private String docResultFields;

	private String language;

	private String searchAppURL;

	private String basePath;

	@Override
	public String getDocResultFields() {
		return docResultFields;
	}

	public void setDocResultFields(String docResultFields) {
		this.docResultFields = docResultFields;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String getSearchAppURL() {
		return searchAppURL;
	}

	public void setSearchAppURL(String searchAppURL) {
		this.searchAppURL = searchAppURL;
	}

	@Override
	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public SearchEngineConfig() {
		super();
	}

	public SearchEngineConfig(String docResultFields, String language, String searchAppURL, String basePath) {
		super();
		this.docResultFields = docResultFields;
		this.language = language;
		this.searchAppURL = searchAppURL;
		this.basePath = basePath;
	}

	@Override
	public String toString() {
		return "SearchEngineConfig [docResultFields=" + docResultFields + ", language=" + language + ", searchAppURL=" + searchAppURL + ", basePath=" + basePath + "]";
	}

}
