package com.app.server.bean;
import java.util.ArrayList;

public class ProjectBean {

	private int projectId;

	private String projectName;

	private String projectDesc;

	private String domainName;

	private int databaseId;

	private String schemaName;

	private String userId;

	private String password;

	private ArrayList<Integer> components;

	private String email;

	public ProjectBean() {
		super();
	}

	@Override
	public String toString() {
		return "ProjectBean [projectId=" + projectId + ", projectName=" + projectName + ", projectDesc=" + projectDesc + ", domainName=" + domainName + ", databaseId="
				+ databaseId + ", schemaName=" + schemaName + ", userId=" + userId + ", password=" + password + ", components=" + components + ", email=" + email + "]";
	}

	public ProjectBean(final int projectId, final String projectName, final String projectDesc, final String domainName, final int databaseId, final String schemaName,
			final String userId, final String password, final ArrayList<Integer> components, final String email) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.domainName = domainName;
		this.databaseId = databaseId;
		this.schemaName = schemaName;
		this.userId = userId;
		this.password = password;
		this.components = components;
		this.email = email;
	}

	public int getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(final int databaseId) {
		this.databaseId = databaseId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(final String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(final String domainName) {
		this.domainName = domainName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(final String schemaName) {
		this.schemaName = schemaName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public ArrayList<Integer> getComponents() {
		return components;
	}

	public void setComponents(final ArrayList<Integer> components) {
		this.components = components;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(final int projectId) {
		this.projectId = projectId;
	}

}
