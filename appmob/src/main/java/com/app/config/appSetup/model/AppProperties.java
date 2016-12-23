package com.app.config.appSetup.model;
public final class AppProperties {
	private final String searchEngineDocResultFields;
	private final String searchengineSearchLanguage;
	private final int authenticationType;
	private final String ldapDomain;
	private final String ldapUrl;
	private final String solrProjectUrl;

	public AppProperties(String searchEngineDocResultFields, String searchengineSearchLanguage, int authenticationType, String ldapDomain, String ldapUrl, String baseProjectUrl) {
		super();
		this.searchEngineDocResultFields = searchEngineDocResultFields;
		this.searchengineSearchLanguage = searchengineSearchLanguage;
		this.authenticationType = authenticationType;
		this.ldapDomain = ldapDomain;
		this.ldapUrl = ldapUrl;
		this.solrProjectUrl = baseProjectUrl;
	}

	public String getSearchEngineDocResultFields() {
		return searchEngineDocResultFields;
	}

	public String getSearchengineSearchLanguage() {
		return searchengineSearchLanguage;
	}

	public int getAuthenticationType() {
		return authenticationType;
	}

	public String getLdapDomain() {
		return ldapDomain;
	}

	public String getLdapUrl() {
		return ldapUrl;
	}

	public String getSolrProjectUrl() {
		return solrProjectUrl;
	}

	@Override
	public String toString() {
		return "AppProperties [searchEngineDocResultFields=" + searchEngineDocResultFields + ", searchengineSearchLanguage=" + searchengineSearchLanguage + ", authenticationType="
				+ authenticationType + ", ldapDomain=" + ldapDomain + ", ldapUrl=" + ldapUrl + ", baseProjectUrl=" + solrProjectUrl + "]";
	}

}
