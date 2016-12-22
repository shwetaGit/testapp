package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;

import com.app.config.annotation.SourceCodeAuthorClass;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;

import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "art_password_algorithm")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "", versionNumber = "1", comments = "art_password_algorithm", complexity = Complexity.LOW)
public class ArtPasswordAlgorithm implements Serializable, Comparator<ArtPasswordAlgorithm> {

	private static final long serialVersionUID = 1L;

	@Column(name = "algorithm")
	@JsonProperty("algorithm")
	@NotNull
	@Min(0)
	@Max(11)
	private Integer algorithm;

	@Column(name = "algoName")
	@JsonProperty("algoName")
	@NotNull
	@Size(min = 0, max = 256)
	private String algoName;

	@Column(name = "algoDescription")
	@JsonProperty("algoDescription")
	@Size(min = 0, max = 256)
	private String algoDescription;

	@Transient
	private String primaryKey;

	@Id
	@Column(name = "algoId")
	@JsonProperty("algoId")
	@GeneratedValue(generator = "UUIDGenerator")
	@Size(min = 0, max = 64)
	private String algoId;

	@Transient
	@JsonIgnore
	private EntityValidatorHelper<Object> entityValidator;

	@Version
	@Column(name = "version_id")
	@JsonProperty("versionId")
	private int versionId;

	@Transient
	private String primaryDisplay;

	public Integer getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(final Integer algorithm) {
		if (algorithm != null) {
			this.algorithm = algorithm;
		}
	}

	public String getAlgoName() {
		return algoName;
	}

	public void setAlgoName(final String algoName) {
		if (algoName != null) {
			this.algoName = algoName;
		}
	}

	public String getAlgoDescription() {
		return algoDescription;
	}

	public void setAlgoDescription(final String algoDescription) {
		this.algoDescription = algoDescription;
	}

	public String getPrimaryKey() {
		return algoId;
	}

	public void setPrimaryKey(final String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String _getPrimarykey() {
		return algoId;
	}

	public String getAlgoId() {
		return algoId;
	}

	public void setAlgoId(final String algoId) {
		this.algoId = algoId;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(final int versionId) {
		this.versionId = versionId;
	}

	public void setPrimaryDisplay(final String primaryDisplay) {
		this.primaryDisplay = primaryDisplay;
	}

	public String getPrimaryDisplay() {
		StringBuilder data = new StringBuilder().append("").append((algoName == null ? " " : algoName));
		return data.toString();
	}

	public String toString() {
		return getPrimaryDisplay();
	}

	public int hashCode() {
		int hashcode=0;
		if (algoId == null) {
			hashcode= super.hashCode();
		} else {
			hashcode= algoId.hashCode();
		}
		return hashcode;
	}

	public boolean equals(final Object obj) {
		boolean isEquals = false;
		try {
			final ArtPasswordAlgorithm other = (ArtPasswordAlgorithm) obj;
			if (algoId == null) {
				isEquals = false;
			} else if (!algoId.equals(other.algoId)) {
				isEquals = false;
			}
		} catch (java.lang.Exception ignore) {
			isEquals = false;
		}
		return isEquals;
	}

	@Override
	public int compare(ArtPasswordAlgorithm arg0, ArtPasswordAlgorithm arg1) {
		return 0;
	}

}
