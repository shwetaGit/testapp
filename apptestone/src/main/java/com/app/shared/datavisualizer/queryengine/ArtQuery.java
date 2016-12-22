package com.app.shared.datavisualizer.queryengine;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import atg.taglib.json.util.JSONObject;

import com.athena.shared.pluggable.entity.ArtEntity;
import com.spartan.server.interfaces.ArtQueryInterface;

@Entity
@Table(name = "art_query")
public class ArtQuery extends ArtEntity implements com.athena.shared.pluggable.entity.Entity, ArtQueryInterface {

	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@Column(name = "query_id")
	private String queryId;

	@Column(name = "jpql_query")
	private String jpqlQuery;

	@Column(name = "query_type")
	private Integer queryType;

	@Column(name = "name")
	private String name;

	@Column(name = "hidden_name")
	private String hiddenName;

	@Column(name = "query_json")
	private String queryJSON;

	@Column(name = "sql_query")
	private String sqlQuery;

	@Column(name = "user_access")
	private boolean userAccess = false;

	public boolean isUserAccess() {
		return userAccess;
	}

	public void setUserAccess(boolean userAccess) {
		this.userAccess = userAccess;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public ArtQuery() {
		super();
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getJpqlQuery() {
		return jpqlQuery;
	}

	public void setJpqlQuery(String jpqlQuery) {
		this.jpqlQuery = jpqlQuery;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHiddenName() {
		return hiddenName;
	}

	public void setHiddenName(String hiddenName) {
		this.hiddenName = hiddenName;
	}

	public String getQueryJSON() throws Exception{
			return this.decodeJSON(queryJSON);
	}

	public void setQueryJSON(String queryJSON) throws Exception {
		this.queryJSON = this.encodeJSON(queryJSON);
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("queryId", queryId);
			json.put("name", name);
			json.put("hiddenName", hiddenName);
			json.put("jpqlQuery", jpqlQuery);
			json.put("queryType", queryType);
			json.put("queryJSON", this.decodeJSON(queryJSON));
		} catch (Exception e) {
		}
		return json;
	}

	@Override
	public String toString() {
		return "AwsQuery [queryId=" + queryId + ", jpqlQuery=" + jpqlQuery + ", queryType=" + queryType + ", name=" + name + ", hiddenName=" + hiddenName + "]";
	}

	@Override
	public Object getPrimaryKey() {
		return queryId;
	}

}
