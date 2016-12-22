package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.ConnectorConfigInterface;

public class ConnectorConfig implements ConnectorConfigInterface {

	private String serverId;

	private String serverName;

	private String ipAddress;

	private String portNo;

	private String databaseName;

	private String userName;

	private String password;

	private Integer connectorType;

	private String protocol;

	private String filePath;

	private String databaseType;

	private String databaseDriverName;

	private String databaseUrl;
	
	private String urlConfig;
	
	@Override
	public String getUrlConfig() {
		return urlConfig;
	}

	@Override
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public ConnectorConfig() {
		super();
	}

	public ConnectorConfig(String serverId, String serverName, String ipAddress, String portNo, String databaseName, String userName, String password, Integer connectorType,
			String protocol, String filePath, String urlConfig, String databaseType, String databaseDriverName, String databaseUrl) {
		super();
		this.serverId = serverId;
		this.serverName = serverName;
		this.ipAddress = ipAddress;
		this.portNo = portNo;
		this.databaseName = databaseName;
		this.userName = userName;
		this.password = password;
		this.connectorType = connectorType;
		this.protocol = protocol;
		this.filePath = filePath;
		this.databaseType = databaseType;
		this.databaseDriverName = databaseDriverName;
		this.databaseUrl = databaseUrl;
		this.urlConfig = urlConfig;
	}

	@Override
	public String getDatabaseType() {
		return databaseType;
	}

	@Override
	public String getDatabaseDriverName() {
		return databaseDriverName;
	}

	@Override
	public String getDatabaseUrl() {
		return databaseUrl;
	}

	@Override
	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	@Override
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Override
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	@Override
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Integer getConnectorType() {
		return connectorType;
	}

	public void setConnectorType(Integer connectorType) {
		this.connectorType = connectorType;
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public void setDatabaseDriverName(String databaseDriverName) {
		this.databaseDriverName = databaseDriverName;
	}

	@Override
	public String toString() {
		return "ConnectorConfig [serverId=" + serverId + ", serverName=" + serverName + ",databaseUrl=" + databaseUrl + ", databaseType=" + databaseType + ", databaseDriverName="
				+ databaseDriverName + ", ipAddress=" + ipAddress + ", portNo=" + portNo + ", databaseName=" + databaseName + ", userName=" + userName + ", password=" + password
				+ ", connectorType=" + connectorType + ", protocol=" + protocol + ", filePath=" + filePath + ", urlConfig=" + urlConfig + "]";
	}
}
