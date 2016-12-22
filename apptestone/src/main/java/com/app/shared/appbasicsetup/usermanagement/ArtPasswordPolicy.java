package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;

import com.app.config.annotation.SourceCodeAuthorClass;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
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

@Table(name = "art_password_policy")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "", versionNumber = "1", comments = "PasswordPolicy", complexity = Complexity.LOW)
public class ArtPasswordPolicy implements Serializable, Comparator<ArtPasswordPolicy> {

	private static final long serialVersionUID = 1L;

	@Column(name = "policyName")
	@JsonProperty("policyName")
	@NotNull
	@Size(min = 0, max = 256)
	private String policyName;

	@Column(name = "policyDescription")
	@JsonProperty("policyDescription")
	@Size(min = 0, max = 256)
	private String policyDescription;

	@Column(name = "maxPwdLength")
	@JsonProperty("maxPwdLength")
	@Min(0)
	@Max(32)
	private Integer maxPwdLength;

	@Column(name = "minPwdLength")
	@JsonProperty("minPwdLength")
	@NotNull
	@Min(0)
	@Max(11)
	private Integer minPwdLength;

	@Column(name = "minCapitalLetters")
	@JsonProperty("minCapitalLetters")
	@Min(0)
	@Max(11)
	private Integer minCapitalLetters;

	@Column(name = "minSmallLetters")
	@JsonProperty("minSmallLetters")
	@Min(0)
	@Max(11)
	private Integer minSmallLetters;

	@Column(name = "minNumericValues")
	@JsonProperty("minNumericValues")
	@Min(0)
	@Max(11)
	private Integer minNumericValues;

	@Column(name = "minSpecialLetters")
	@JsonProperty("minSpecialLetters")
	@Min(0)
	@Max(11)
	private Integer minSpecialLetters;

	@Column(name = "allowedSpecialLetters")
	@JsonProperty("allowedSpecialLetters")
	@Size(min = 0, max = 256)
	private String allowedSpecialLetters;

	@Transient
	private String primaryKey;

	@Id
	@Column(name = "policyId")
	@JsonProperty("policyId")
	@GeneratedValue(generator = "UUIDGenerator")
	@Size(min = 0, max = 64)
	private String policyId;

	@Transient
	@JsonIgnore
	private EntityValidatorHelper<Object> entityValidator;

	@Version
	@Column(name = "version_id")
	@JsonProperty("versionId")
	private int versionId;

	@Transient
	private String primaryDisplay;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(final String policyName) {
		if (policyName != null) {
			this.policyName = policyName;
		}
	}

	public String getPolicyDescription() {
		return policyDescription;
	}

	public void setPolicyDescription(final String policyDescription) {
		this.policyDescription = policyDescription;
	}

	public Integer getMaxPwdLength() {
		return maxPwdLength;
	}

	public void setMaxPwdLength(final Integer maxPwdLength) {
		this.maxPwdLength = maxPwdLength;
	}

	public Integer getMinPwdLength() {
		return minPwdLength;
	}

	public void setMinPwdLength(final Integer minPwdLength) {
		if (minPwdLength != null) {
			this.minPwdLength = minPwdLength;
		}
	}

	public Integer getMinCapitalLetters() {
		return minCapitalLetters;
	}

	public void setMinCapitalLetters(final Integer minCapitalLetters) {
		this.minCapitalLetters = minCapitalLetters;
	}

	public Integer getMinSmallLetters() {
		return minSmallLetters;
	}

	public void setMinSmallLetters(final Integer minSmallLetters) {
		this.minSmallLetters = minSmallLetters;
	}

	public Integer getMinNumericValues() {
		return minNumericValues;
	}

	public void setMinNumericValues(final Integer minNumericValues) {
		this.minNumericValues = minNumericValues;
	}

	public Integer getMinSpecialLetters() {
		return minSpecialLetters;
	}

	public void setMinSpecialLetters(final Integer minSpecialLetters) {
		this.minSpecialLetters = minSpecialLetters;
	}

	public String getAllowedSpecialLetters() {
		return allowedSpecialLetters;
	}

	public void setAllowedSpecialLetters(final String allowedSpecialLetters) {
		this.allowedSpecialLetters = allowedSpecialLetters;
	}

	public String getPrimaryKey() {
		return policyId;
	}

	public void setPrimaryKey(final String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String _getPrimarykey() {
		return policyId;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(final String policyId) {
		this.policyId = policyId;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(final int _versionId) {
		this.versionId = versionId;
	}

	public void setPrimaryDisplay(final String _primaryDisplay) {
		this.primaryDisplay = primaryDisplay;
	}

	@Override
	public int compare(ArtPasswordPolicy object1, ArtPasswordPolicy object2) {
		return 0;
	}

	public String getPrimaryDisplay() {
		StringBuilder data = new StringBuilder().append("").append((policyName == null ? " " : policyName));
		return data.toString();
	}

	public String toString() {
		return getPrimaryDisplay();
	}

	public int hashCode() {
		int hashCode = 0;
		if (policyId == null) {
			hashCode = super.hashCode();
		} else {
			hashCode = policyId.hashCode();
		}
		return hashCode;
	}

	public boolean equals(final Object obj) {
		boolean isEquals = false;
		try {
			final ArtPasswordPolicy other = (ArtPasswordPolicy) obj;
			if (policyId == null) {
				isEquals = false;
			} else if (!policyId.equals(other.policyId)) {
				isEquals = false;
			}
		} catch (java.lang.Exception ignore) {
			isEquals = false;
		}
		return isEquals;
	}
}
