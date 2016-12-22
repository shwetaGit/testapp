package com.app.config.appSetup.model;
public class SolrProperties {

	private String solrHome;

	public SolrProperties() {
		super();
	}

	public SolrProperties(String solrHome) {
		super();
		this.solrHome = solrHome;
	}

	public String getSolrHome() {
		return solrHome;
	}

	public void setSolrHome(String solrHome) {
		this.solrHome = solrHome;
	}

	@Override
	public String toString() {
		return "SolrProperties [solrHome=" + solrHome + "]";
	}

}
