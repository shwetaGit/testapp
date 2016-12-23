package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.PathConfigInterface;

public class PathConfig implements PathConfigInterface {

	private String basePath;
	private String webAppPath;
	private String os;

	@Override
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	@Override
	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	@Override
	public String getWebAppPath() {
		return webAppPath;
	}

	public void setWebAppPath(String webAppPath) {
		this.webAppPath = webAppPath;
	}

	@Override
	public String toString() {
		return "PathConfig [basePath=" + basePath + ", webAppPath=" + webAppPath + "]";
	}

	public PathConfig(String basePath, String webAppPath) {
		super();
		this.basePath = basePath;
		this.webAppPath = webAppPath;
	}

	public PathConfig() {
		super();
	}

}
