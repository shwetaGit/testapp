package com.app.shared.appinsight.alarms;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "art_bounded_context")
public class ArtBoundedContext {

	@Transient
	private String primaryKey;

	@Id
	@Column(name = "boundedContextId")
	@JsonProperty("boundedContextId")
	@GeneratedValue(generator = "UUIDGenerator")
	private String boundedContextId;

	@Column(name = "boundedContextName")
	private String boundedContextName;

	@Column(name = "hiddenContextName")
	private String hiddenContextName;

	@Column(name = "alarmPrefix")
	private String alarmPrefix;

	@Column(name = "isDefault")
	private boolean isDefault;

	@Column(name = "isSystemDefined")
	private boolean isSystemDefined;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "boundedContextId", columnDefinition = "boundedContextId", referencedColumnName = "boundedContextId")
	private List<ArtDomain> artDomain;

	public ArtBoundedContext(String boundedContextName, String hiddenContextName, String alarmPrefix, boolean isDefault, boolean isSystemDefined) {
		super();
		this.boundedContextName = boundedContextName;
		this.hiddenContextName = hiddenContextName;
		this.alarmPrefix = alarmPrefix;
		this.isDefault = isDefault;
		this.isSystemDefined = isSystemDefined;
	}

	public ArtBoundedContext() {
		super();
	}

	public boolean isIsDefault() {
		return isDefault;
	}

	public void setIsIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean isIsSystemDefined() {
		return isSystemDefined;
	}

	public void setIsIsSystemDefined(boolean isSystemDefined) {
		this.isSystemDefined = isSystemDefined;
	}

	public String getBoundedContextName() {
		return boundedContextName;
	}

	public void setBoundedContextName(final String boundedContextName) {
		this.boundedContextName = boundedContextName;
	}

	public String getHiddenContextName() {
		return hiddenContextName;
	}

	public void setHiddenContextName(final String hiddenContextName) {
		this.hiddenContextName = hiddenContextName;
	}

	public List<ArtDomain> getArtDomain() {
		return artDomain;
	}

	public void setArtDomain(final List<ArtDomain> artDomain) {
		this.artDomain = artDomain;
	}

	public String getAlarmPrefix() {
		return alarmPrefix;
	}

	public void setAlarmPrefix(final String alarmPrefix) {
		this.alarmPrefix = alarmPrefix;
	}

	public String getBoundedContextId() {
		return boundedContextId;
	}

	public void setBoundedContextId(final String boundedContextId) {
		this.boundedContextId = boundedContextId;
	}

	public String toJSON() {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("boundedContextId", boundedContextId);
			jsonObj.put("boundedContextName", boundedContextName);
			jsonObj.put("hiddenContextName", hiddenContextName);
			jsonObj.put("alarmPrefix", alarmPrefix);
			jsonObj.put("isDefault", isDefault);
			jsonObj.put("isSystemDefined", isSystemDefined);
			// jsonObj.put("artDomain", artDomain);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObj.toString();
	}
}
