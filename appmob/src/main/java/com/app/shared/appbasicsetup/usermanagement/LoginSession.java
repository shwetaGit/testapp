package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.server.interfaces.LoginSessionInterface;
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
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

@Table(name = "LoginSession")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "LoginSession", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "LoginSessionResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.appbasicsetup.usermanagement.LoginSession.class, columns = { @javax.persistence.ColumnResult(name = "appSessionId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "userId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "userdetailAuthAlgo", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "appServerSessionId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "loginTime", type = java.sql.Timestamp.class), @javax.persistence.ColumnResult(name = "logoutTime", type = java.sql.Timestamp.class), @javax.persistence.ColumnResult(name = "lastAccessTime", type = java.sql.Timestamp.class), @javax.persistence.ColumnResult(name = "clientIPAddress", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "clientIPAddressInt", type = java.lang.Long.class), @javax.persistence.ColumnResult(name = "clientNetworkAddress", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "clientBrowser", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "sessionData", type = java.lang.String.class) }))
public class LoginSession implements Serializable, CommonEntityInterface, LoginSessionInterface, Comparator<LoginSession> {

    private static final long serialVersionUID = 1482410095041L;

    @Column(name = "AppServerSessionId")
    @JsonProperty("appServerSessionId")
    @NotNull
    @Size(min = 12, max = 256)
    private String appServerSessionId;

    @Column(name = "loginTime")
    @JsonProperty("loginTime")
    @NotNull
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp loginTime;

    @Column(name = "lastAccessTime")
    @JsonProperty("lastAccessTime")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp lastAccessTime;

    @Column(name = "logoutTime")
    @JsonProperty("logoutTime")
    @NotNull
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp logoutTime;

    @Column(name = "clientIPAddress")
    @JsonProperty("clientIPAddress")
    @NotNull
    @Size(min = 0, max = 128)
    private String clientIPAddress;

    @Column(name = "clientIPAddressInt")
    @JsonProperty("clientIPAddressInt")
    private Long clientIPAddressInt;

    @Column(name = "clientNetworkAddress")
    @JsonProperty("clientNetworkAddress")
    @Max(11)
    private Integer clientNetworkAddress;

    @Column(name = "clientBrowser")
    @JsonProperty("clientBrowser")
    @NotNull
    @Size(min = 0, max = 256)
    private String clientBrowser;

    @Column(name = "sessionData")
    @JsonProperty("sessionData")
    @Size(max = 5120)
    private String sessionData;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "AppSessionId")
    @JsonProperty("appSessionId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 12, max = 256)
    private String appSessionId;

    @Column(name = "userId")
    @JsonProperty("userId")
    private String userId;

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
    private String userdetailAuthAlgo;

    public LoginSession() {
    }

    public LoginSession(String appSessionId, String userId, String userdetailAuthAlgo, String appServerSessionId, Timestamp loginTime, Timestamp logoutTime, Timestamp lastAccessTime, String clientIPAddress, Long clientIPAddressInt, Integer clientNetworkAddress, String clientBrowser, String sessionData) {
        this.appSessionId = appSessionId;
        this.userId = userId;
        this.userdetailAuthAlgo = userdetailAuthAlgo;
        this.appServerSessionId = appServerSessionId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.lastAccessTime = lastAccessTime;
        this.clientIPAddress = clientIPAddress;
        this.clientIPAddressInt = clientIPAddressInt;
        this.clientNetworkAddress = clientNetworkAddress;
        this.clientBrowser = clientBrowser;
        this.sessionData = sessionData;
    }

    public String getAppServerSessionId() {
        return appServerSessionId;
    }

    public void setAppServerSessionId(String appServerSessionId) {
        if (appServerSessionId != null) {
            this.appServerSessionId = appServerSessionId;
        }
    }

    public Timestamp getLoginTime() {
        return (loginTime == null ? loginTime : new Timestamp(loginTime.getTime()));
    }

    public void setLoginTime(Timestamp loginTime) {
        if (loginTime != null) {
            this.loginTime = new Timestamp(loginTime.getTime());
        }
    }

    public Timestamp getLastAccessTime() {
        return (lastAccessTime == null ? lastAccessTime : new Timestamp(lastAccessTime.getTime()));
    }

    public void setLastAccessTime(Timestamp lastAccessTime) {
        this.lastAccessTime = (lastAccessTime == null ? lastAccessTime : new Timestamp(lastAccessTime.getTime()));
    }

    public Timestamp getLogoutTime() {
        return (logoutTime == null ? logoutTime : new Timestamp(logoutTime.getTime()));
    }

    public void setLogoutTime(Timestamp logoutTime) {
        if (logoutTime != null) {
            this.logoutTime = new Timestamp(logoutTime.getTime());
        }
    }

    public String getClientIPAddress() {
        return clientIPAddress;
    }

    public void setClientIPAddress(String clientIPAddress) {
        if (clientIPAddress != null) {
            this.clientIPAddress = clientIPAddress;
        }
    }

    public Long getClientIPAddressInt() {
        return clientIPAddressInt;
    }

    public void setClientIPAddressInt(Long clientIPAddressInt) {
        this.clientIPAddressInt = clientIPAddressInt;
    }

    public Integer getClientNetworkAddress() {
        return clientNetworkAddress;
    }

    public void setClientNetworkAddress(Integer clientNetworkAddress) {
        this.clientNetworkAddress = clientNetworkAddress;
    }

    public String getClientBrowser() {
        return clientBrowser;
    }

    public void setClientBrowser(String clientBrowser) {
        if (clientBrowser != null) {
            this.clientBrowser = clientBrowser;
        }
    }

    public String getSessionData() {
        return sessionData;
    }

    public void setSessionData(String sessionData) {
        this.sessionData = sessionData;
    }

    public String getPrimaryKey() {
        return appSessionId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return appSessionId;
    }

    public String getAppSessionId() {
        return appSessionId;
    }

    public void setAppSessionId(String appSessionId) {
        this.appSessionId = appSessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserdetailAuthAlgo() {
        return userdetailAuthAlgo;
    }

    public void setUserdetailAuthAlgo(String userdetailAuthAlgo) {
        this.userdetailAuthAlgo = userdetailAuthAlgo;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param LoginSession
     * @param LoginSession
     */
    @Override
    public int compare(LoginSession object1, LoginSession object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((appServerSessionId == null ? " " : appServerSessionId) + ",").append((loginTime == null ? " " : loginTime) + ",").append((logoutTime == null ? " " : logoutTime) + ",").append((lastAccessTime == null ? " " : lastAccessTime));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (appSessionId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = appSessionId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.usermanagement.LoginSession other = (com.app.shared.appbasicsetup.usermanagement.LoginSession) obj;
            if (appSessionId == null) {
                isEquals = false;
            } else if (!appSessionId.equals(other.appSessionId)) {
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

    @Override
    public Integer getActiveStatus() {
        return this.systemInfo.getActiveStatus();
    }
}
