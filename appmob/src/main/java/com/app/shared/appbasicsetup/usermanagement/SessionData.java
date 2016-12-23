package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.server.interfaces.SessionDataInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

@Table(name = "SessionData")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "SessionData", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "SessionData.FindByAppSessionId", query = "select e from SessionData e where e.appSessionId=:appSessionId AND e.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "SessionData.FindBySessionKey", query = "select e from SessionData e where e.appSessionId=:appSessionId and e.sessionKey =:sessionKey AND e.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "SessionData.DeleteAllSessionData", query = "delete from SessionData e where e.appSessionId=:appSessionId AND e.systemInfo.activeStatus=1") })
public class SessionData implements Serializable, CommonEntityInterface, SessionDataInterface, Comparator<SessionData> {

    private static final long serialVersionUID = 1482410096456L;

    @Column(name = "customerId")
    @JsonProperty("customerId")
    @Size(max = 64)
    private String customerId;

    @Column(name = "userId")
    @JsonProperty("userId")
    @NotNull
    @Size(min = 0, max = 64)
    private String userId;

    @Column(name = "sessionKey")
    @JsonProperty("sessionKey")
    @NotNull
    @Size(min = 0, max = 64)
    private String sessionKey;

    @Column(name = "dataType")
    @JsonProperty("dataType")
    @NotNull
    @Min(0)
    @Max(2147483647)
    private Integer dataType;

    @Column(name = "numberValue")
    @JsonProperty("numberValue")
    @Max(2147483647)
    private Integer numberValue;

    @Column(name = "dateTimeValue")
    @JsonProperty("dateTimeValue")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp dateTimeValue;

    @Column(name = "stringValue")
    @JsonProperty("stringValue")
    @Size(max = 2000)
    private String stringValue;

    @Column(name = "booleanValue")
    @JsonProperty("booleanValue")
    private Boolean booleanValue;

    @Column(name = "jsonValue")
    @JsonProperty("jsonValue")
    private String jsonValue;

    @Column(name = "appSessionId")
    @JsonProperty("appSessionId")
    @NotNull
    @Size(min = 3, max = 256)
    private String appSessionId;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "dataId")
    @JsonProperty("dataId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 256)
    private String dataId;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId != null) {
            this.userId = userId;
        }
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        if (sessionKey != null) {
            this.sessionKey = sessionKey;
        }
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        if (dataType != null) {
            this.dataType = dataType;
        }
    }

    public Integer getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
    }

    public Timestamp getDateTimeValue() {
        return (dateTimeValue == null ? dateTimeValue : new Timestamp(dateTimeValue.getTime()));
    }

    public void setDateTimeValue(Timestamp dateTimeValue) {
        this.dateTimeValue = (dateTimeValue == null ? dateTimeValue : new Timestamp(dateTimeValue.getTime()));
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public String getJsonValue() {
        return jsonValue;
    }

    public void setJsonValue(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String getAppSessionId() {
        return appSessionId;
    }

    public void setAppSessionId(String appSessionId) {
        if (appSessionId != null) {
            this.appSessionId = appSessionId;
        }
    }

    public String getPrimaryKey() {
        return dataId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return dataId;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
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
     * @param SessionData
     * @param SessionData
     */
    @Override
    public int compare(SessionData object1, SessionData object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (dataId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = dataId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.usermanagement.SessionData other = (com.app.shared.appbasicsetup.usermanagement.SessionData) obj;
            if (dataId == null) {
                isEquals = false;
            } else if (!dataId.equals(other.dataId)) {
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
