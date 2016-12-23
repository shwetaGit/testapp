package com.app.shared.appinsight.alarms;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import com.app.shared.SystemInfo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.athena.framework.server.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@SuppressWarnings("serial")
@Table(name = "art_domain")
@Entity
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "domainId")
public class ArtDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "domainName")
	@JsonProperty("domainName")
	@NotNull
	@Size(min = 1, max = 256)
	private String domainName;

	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@Column(name = "domainId")
	@JsonProperty("domainId")
	@NotNull
	@Size(min = 1, max = 256)
	private String domainId;

	@Column(name = "hiddenContextName")
	@JsonProperty("hiddenContextName")
	@NotNull
	private String hiddenContextName;

	@Column(name = "alarmPrefix")
	@JsonProperty("alarmPrefix")
	@NotNull
	private String alarmPrefix;

	@Id
	@Column(name = "isDefault")
	@JsonProperty("isDefault")
	private boolean isDefault;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "boundedContextId", referencedColumnName = "boundedContextId")
	private ArtBoundedContext artBoundedContext;

	public ArtDomain(String domainName, String hiddenContextName, String alarmPrefix, boolean isDefault, ArtBoundedContext artBoundedContext) {
		super();
		this.domainName = domainName;
		this.hiddenContextName = hiddenContextName;
		this.alarmPrefix = alarmPrefix;
		this.isDefault = isDefault;
		this.artBoundedContext = artBoundedContext;
	}

	public ArtDomain() {
		super();
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(final String _domainName) {
		if (_domainName != null) {
			this.domainName = _domainName;
		}
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(final String _dominId) {
		if (_dominId != null) {
			this.domainId = _dominId;
		}
	}

	public ArtBoundedContext getArtBoundedContext() {
		return artBoundedContext;
	}

	public void setArtBoundedContext(ArtBoundedContext artBoundedContext) {
		this.artBoundedContext = artBoundedContext;
	}

	public String getHiddenContextName() {
		return hiddenContextName;
	}

	public void setHiddenContextName(String hiddenContextName) {
		this.hiddenContextName = hiddenContextName;
	}

	public boolean getIsIsDefault() {
		return isDefault;
	}

	public void setIsIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getAlarmPrefix() {
		return alarmPrefix;
	}

	public void setAlarmPrefix(String alarmPrefix) {
		this.alarmPrefix = alarmPrefix;
	}
}
