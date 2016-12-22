package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.server.password.interfaces.PasswordPolicyInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "PasswordPolicy")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "PasswordPolicy", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "PasswordPolicy.findAll", query = " select u from PasswordPolicy u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "PasswordPolicy.DefaultFinders", query = "select e from PasswordPolicy e where e.systemInfo.activeStatus=1 and e.policyName LIKE :policyName"), @javax.persistence.NamedQuery(name = "PasswordPolicy.findById", query = "select e from PasswordPolicy e where e.systemInfo.activeStatus=1 and e.policyId =:policyId") })
public class PasswordPolicy implements Serializable, CommonEntityInterface, PasswordPolicyInterface, Comparator<PasswordPolicy> {

    private static final long serialVersionUID = 1482410098703L;

    @Column(name = "policyName")
    @JsonProperty("policyName")
    @NotNull
    @Size(min = 0, max = 256)
    private String policyName;

    @Column(name = "policyDescription")
    @JsonProperty("policyDescription")
    @Size(max = 256)
    private String policyDescription;

    @Column(name = "maxPwdLength")
    @JsonProperty("maxPwdLength")
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
    @Max(11)
    private Integer minCapitalLetters;

    @Column(name = "minSmallLetters")
    @JsonProperty("minSmallLetters")
    @Max(11)
    private Integer minSmallLetters;

    @Column(name = "minNumericValues")
    @JsonProperty("minNumericValues")
    @Max(11)
    private Integer minNumericValues;

    @Column(name = "minSpecialLetters")
    @JsonProperty("minSpecialLetters")
    @Max(11)
    private Integer minSpecialLetters;

    @Column(name = "allowedSpecialLetters")
    @JsonProperty("allowedSpecialLetters")
    @Size(max = 256)
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
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private Integer versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    @Transient
    @JsonIgnore
    private boolean isEntityValidated = false;

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        if (policyName != null) {
            this.policyName = policyName;
        }
    }

    public String getPolicyDescription() {
        return policyDescription;
    }

    public void setPolicyDescription(String policyDescription) {
        this.policyDescription = policyDescription;
    }

    public Integer getMaxPwdLength() {
        return maxPwdLength;
    }

    public void setMaxPwdLength(Integer maxPwdLength) {
        this.maxPwdLength = maxPwdLength;
    }

    public Integer getMinPwdLength() {
        return minPwdLength;
    }

    public void setMinPwdLength(Integer minPwdLength) {
        if (minPwdLength != null) {
            this.minPwdLength = minPwdLength;
        }
    }

    public Integer getMinCapitalLetters() {
        return minCapitalLetters;
    }

    public void setMinCapitalLetters(Integer minCapitalLetters) {
        this.minCapitalLetters = minCapitalLetters;
    }

    public Integer getMinSmallLetters() {
        return minSmallLetters;
    }

    public void setMinSmallLetters(Integer minSmallLetters) {
        this.minSmallLetters = minSmallLetters;
    }

    public Integer getMinNumericValues() {
        return minNumericValues;
    }

    public void setMinNumericValues(Integer minNumericValues) {
        this.minNumericValues = minNumericValues;
    }

    public Integer getMinSpecialLetters() {
        return minSpecialLetters;
    }

    public void setMinSpecialLetters(Integer minSpecialLetters) {
        this.minSpecialLetters = minSpecialLetters;
    }

    public String getAllowedSpecialLetters() {
        return allowedSpecialLetters;
    }

    public void setAllowedSpecialLetters(String allowedSpecialLetters) {
        this.allowedSpecialLetters = allowedSpecialLetters;
    }

    public String getPrimaryKey() {
        return policyId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return policyId;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public void setPrimaryDisplay(String primaryDisplay) {
        this.primaryDisplay = primaryDisplay;
    }

    /**
     * Returns boolean value if System information's active status =-1
     * @return boolean
     */
    @JsonIgnore
    public boolean isHardDelete() {
        boolean isHardDelete = false;
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            isHardDelete = true;
        } else {
            isHardDelete = false;
        }
        return isHardDelete;
    }

    /**
     * Validates the entity fields based on java.validation.constraints annotaions and sets isEntityValided value in System information object
     * @return boolean
     * @throws java.lang.SecurityException
     */
    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    /**
     * Sets EntityValidator object
     * @param validateFactory
     */
    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> validateFactory) {
        this.entityValidator = validateFactory;
    }

    /**
     * Creates a new entity audit object and  if primarykey fields are null then sets values in the entity audit field.
     * @param customerId
     * @param userId
     */
    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    /**
     * Creates a new entity audit object and System Information object and based on @params RECORD_TYPE sets created by and updated by values in the entity audit field.
     * @param CustomerId
     * @param userId
     * @param RECORD_TYPE
     */
    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    /**
     * Returns Logged in user informatio.
     * @return auditInfo
     */
    @JsonIgnore
    public String getLoggedInUserInfo() {
        return "";
    }

    /**
     * Creates new System Information object.
     * @param RECORD_TYPE
     */
    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    /**
     * Sets active status in System Information object.
     * @param activeStatus
     */
    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    /**
     * Returns system information object.
     * @return systemInfo
     */
    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    /**
     * Creates System information obect if null and sets transaction access code in that object.
     * @param transactionAccessCode
     */
    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
    }

    /**
     * Compares 2 objects and returns integer value
     * @param PasswordPolicy
     * @param PasswordPolicy
     */
    @Override
    public int compare(PasswordPolicy object1, PasswordPolicy object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((policyName == null ? " " : policyName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (policyId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = policyId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.usermanagement.PasswordPolicy other = (com.app.shared.appbasicsetup.usermanagement.PasswordPolicy) obj;
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

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isEntityValidated;
    }

    @JsonIgnore
    public void setEntityValidated(boolean isEntityValidated) {
        this.isEntityValidated = isEntityValidated;
    }
}
