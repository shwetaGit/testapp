package com.app.shared.organization.locationmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
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
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import javax.persistence.SqlResultSetMapping;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "State")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "State", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "StateResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.organization.locationmanagement.State.class, columns = { @javax.persistence.ColumnResult(name = "stateId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "countryId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "countryCountryName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateCode", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "stateCodeChar2", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateCodeChar3", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateDescription", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateFlag", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateCapital", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateCapitalLatitude", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "stateCapitalLongitude", type = java.lang.Integer.class) }))
@NamedQueries({ @javax.persistence.NamedQuery(name = "State.findAll", query = " select u from State u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "State.DefaultFinders", query = "select e from State e where e.systemInfo.activeStatus=1 and e.countryId LIKE :countryId and e.stateName LIKE :stateName and e.stateCode BETWEEN :minstateCode AND :maxstateCode and e.stateCodeChar2 LIKE :stateCodeChar2 and e.stateCodeChar3 LIKE :stateCodeChar3"), @javax.persistence.NamedQuery(name = "State.findByCountryId", query = "select e from State e where e.systemInfo.activeStatus=1 and e.countryId=:countryId"), @javax.persistence.NamedQuery(name = "State.findById", query = "select e from State e where e.systemInfo.activeStatus=1 and e.stateId =:stateId") })
public class State implements Serializable, CommonEntityInterface, Comparator<State> {

    private static final long serialVersionUID = 1482410073273L;

    @Column(name = "stateName")
    @JsonProperty("stateName")
    @NotNull
    @Size(min = 0, max = 256)
    private String stateName;

    @Column(name = "stateCode")
    @JsonProperty("stateCode")
    @Max(2)
    private Integer stateCode;

    @Column(name = "stateCodeChar2")
    @JsonProperty("stateCodeChar2")
    @NotNull
    @Size(min = 0, max = 32)
    private String stateCodeChar2;

    @Column(name = "stateCodeChar3")
    @JsonProperty("stateCodeChar3")
    @Size(max = 32)
    private String stateCodeChar3;

    @Column(name = "stateDescription")
    @JsonProperty("stateDescription")
    @Size(max = 256)
    private String stateDescription;

    @Column(name = "stateFlag")
    @JsonProperty("stateFlag")
    @Size(max = 128)
    private String stateFlag;

    @Column(name = "stateCapital")
    @JsonProperty("stateCapital")
    @Size(max = 128)
    private String stateCapital;

    @Column(name = "stateCapitalLatitude")
    @JsonProperty("stateCapitalLatitude")
    @Max(11)
    private Integer stateCapitalLatitude;

    @Column(name = "stateCapitalLongitude")
    @JsonProperty("stateCapitalLongitude")
    @Max(11)
    private Integer stateCapitalLongitude;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "stateId")
    @JsonProperty("stateId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String stateId;

    @Column(name = "countryId")
    @JsonProperty("countryId")
    private String countryId;

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

    @Transient
    private String countryCountryName;

    public State() {
    }

    public State(String stateId, String countryId, String countryCountryName, String stateName, Integer stateCode, String stateCodeChar2, String stateCodeChar3, String stateDescription, String stateFlag, String stateCapital, Integer stateCapitalLatitude, Integer stateCapitalLongitude) {
        this.stateId = stateId;
        this.countryId = countryId;
        this.countryCountryName = countryCountryName;
        this.stateName = stateName;
        this.stateCode = stateCode;
        this.stateCodeChar2 = stateCodeChar2;
        this.stateCodeChar3 = stateCodeChar3;
        this.stateDescription = stateDescription;
        this.stateFlag = stateFlag;
        this.stateCapital = stateCapital;
        this.stateCapitalLatitude = stateCapitalLatitude;
        this.stateCapitalLongitude = stateCapitalLongitude;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        if (stateName != null) {
            this.stateName = stateName;
        }
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateCodeChar2() {
        return stateCodeChar2;
    }

    public void setStateCodeChar2(String stateCodeChar2) {
        if (stateCodeChar2 != null) {
            this.stateCodeChar2 = stateCodeChar2;
        }
    }

    public String getStateCodeChar3() {
        return stateCodeChar3;
    }

    public void setStateCodeChar3(String stateCodeChar3) {
        this.stateCodeChar3 = stateCodeChar3;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    public String getStateFlag() {
        return stateFlag;
    }

    public void setStateFlag(String stateFlag) {
        this.stateFlag = stateFlag;
    }

    public String getStateCapital() {
        return stateCapital;
    }

    public void setStateCapital(String stateCapital) {
        this.stateCapital = stateCapital;
    }

    public Integer getStateCapitalLatitude() {
        return stateCapitalLatitude;
    }

    public void setStateCapitalLatitude(Integer stateCapitalLatitude) {
        this.stateCapitalLatitude = stateCapitalLatitude;
    }

    public Integer getStateCapitalLongitude() {
        return stateCapitalLongitude;
    }

    public void setStateCapitalLongitude(Integer stateCapitalLongitude) {
        this.stateCapitalLongitude = stateCapitalLongitude;
    }

    public String getPrimaryKey() {
        return stateId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return stateId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
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

    public String getCountryCountryName() {
        return countryCountryName;
    }

    public void setCountryCountryName(String countryCountryName) {
        this.countryCountryName = countryCountryName;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param State
     * @param State
     */
    @Override
    public int compare(State object1, State object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((stateName == null ? " " : stateName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (stateId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = stateId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.organization.locationmanagement.State other = (com.app.shared.organization.locationmanagement.State) obj;
            if (stateId == null) {
                isEquals = false;
            } else if (!stateId.equals(other.stateId)) {
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
