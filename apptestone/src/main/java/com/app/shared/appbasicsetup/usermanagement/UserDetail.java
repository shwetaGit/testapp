package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.server.interfaces.UserInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.Max;
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.Size;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import javax.persistence.SqlResultSetMapping;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import java.lang.Override;
import javax.persistence.NamedQueries;
import com.spartan.server.interfaces.UserDataInterface;

@Table(name = "UserDetail")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "UserDetail", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "UserDetailResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.appbasicsetup.usermanagement.UserDetail.class, columns = { @javax.persistence.ColumnResult(name = "userId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "userAccessCode", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "userAccessLevelId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "useraccesslevelUserAccessLevel", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "useraccesslevelLevelName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "useraccesslevelLevelDescription", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "userAccessDomainId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "useraccessdomainUserAccessDomain", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "useraccessdomainDomainName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "useraccessdomainDomainDescription", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "multiFactorAuthEnabled", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "genTempOneTimeAuth", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "allowMultipleLogin", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "isLocked", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "isDeleted", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "changeAuthNextLogin", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "authExpiryDate", type = java.sql.Timestamp.class), @javax.persistence.ColumnResult(name = "authAlgo", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "lastAuthChangeDate", type = java.sql.Timestamp.class), @javax.persistence.ColumnResult(name = "sessionTimeout", type = java.lang.Integer.class) }))
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "userId")
@NamedQueries({ @javax.persistence.NamedQuery(name = "UserDetail.findAll", query = " select u from UserDetail u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "UserDetail.findByUserAccessLevelId", query = "select e from UserDetail e where e.systemInfo.activeStatus=1 and e.userAccessLevelId=:userAccessLevelId"), @javax.persistence.NamedQuery(name = "UserDetail.findByUserAccessDomainId", query = "select e from UserDetail e where e.systemInfo.activeStatus=1 and e.userAccessDomainId=:userAccessDomainId"), @javax.persistence.NamedQuery(name = "UserDetail.findById", query = "select e from UserDetail e where e.systemInfo.activeStatus=1 and e.userId =:userId") })
public class UserDetail implements Serializable, CommonEntityInterface, UserInterface, Comparator<UserDetail> {

    private static final long serialVersionUID = 1482410100691L;

    @Column(name = "userAccessCode")
    @JsonProperty("userAccessCode")
    @Max(60000)
    private Integer userAccessCode;

    @Column(name = "multiFactorAuthEnabled")
    @JsonProperty("multiFactorAuthEnabled")
    @Max(1)
    private Integer multiFactorAuthEnabled;

    @Column(name = "genTempOneTimeAuth")
    @JsonProperty("genTempOneTimeAuth")
    @Max(1)
    private Integer genTempOneTimeAuth;

    @Column(name = "allowMultipleLogin")
    @JsonProperty("allowMultipleLogin")
    @Max(1)
    private Integer allowMultipleLogin;

    @Column(name = "isLocked")
    @JsonProperty("isLocked")
    @Max(1)
    private Integer isLocked;

    @Column(name = "isDeleted")
    @JsonProperty("isDeleted")
    @Max(1)
    private Integer isDeleted;

    @Column(name = "changeAuthNextLogin")
    @JsonProperty("changeAuthNextLogin")
    @Max(1)
    private Integer changeAuthNextLogin;

    @Column(name = "authExpiryDate")
    @JsonProperty("authExpiryDate")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp authExpiryDate;

    @Column(name = "authAlgo")
    @JsonProperty("authAlgo")
    @Size(max = 64)
    private String authAlgo = "1";

    @Column(name = "lastAuthChangeDate")
    @JsonProperty("lastAuthChangeDate")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp lastAuthChangeDate;

    @Column(name = "sessionTimeout")
    @JsonProperty("sessionTimeout")
    @Max(3600)
    private Integer sessionTimeout = 1800;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "userId")
    @JsonProperty("userId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String userId;

    @Column(name = "userAccessLevelId")
    @JsonProperty("userAccessLevelId")
    private String userAccessLevelId;

    @Column(name = "userAccessDomainId")
    @JsonProperty("userAccessDomainId")
    private String userAccessDomainId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetail")
    private List<PassRecovery> passRecovery;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetail")
    private UserData userData;

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
    private Integer useraccesslevelUserAccessLevel;

    @Transient
    private String useraccesslevelLevelName;

    @Transient
    private String useraccesslevelLevelDescription;

    @Transient
    private Integer useraccessdomainUserAccessDomain;

    @Transient
    private String useraccessdomainDomainName;

    @Transient
    private String useraccessdomainDomainDescription;

    public UserDetail() {
    }

    public UserDetail(String userId, Integer userAccessCode, String userAccessLevelId, Integer useraccesslevelUserAccessLevel, String useraccesslevelLevelName, String useraccesslevelLevelDescription, String userAccessDomainId, Integer useraccessdomainUserAccessDomain, String useraccessdomainDomainName, String useraccessdomainDomainDescription, Integer multiFactorAuthEnabled, Integer genTempOneTimeAuth, Integer allowMultipleLogin, Integer isLocked, Integer isDeleted, Integer changeAuthNextLogin, Timestamp authExpiryDate, String authAlgo, Timestamp lastAuthChangeDate, Integer sessionTimeout) {
        this.userId = userId;
        this.userAccessCode = userAccessCode;
        this.userAccessLevelId = userAccessLevelId;
        this.useraccesslevelUserAccessLevel = useraccesslevelUserAccessLevel;
        this.useraccesslevelLevelName = useraccesslevelLevelName;
        this.useraccesslevelLevelDescription = useraccesslevelLevelDescription;
        this.userAccessDomainId = userAccessDomainId;
        this.useraccessdomainUserAccessDomain = useraccessdomainUserAccessDomain;
        this.useraccessdomainDomainName = useraccessdomainDomainName;
        this.useraccessdomainDomainDescription = useraccessdomainDomainDescription;
        this.multiFactorAuthEnabled = multiFactorAuthEnabled;
        this.genTempOneTimeAuth = genTempOneTimeAuth;
        this.allowMultipleLogin = allowMultipleLogin;
        this.isLocked = isLocked;
        this.isDeleted = isDeleted;
        this.changeAuthNextLogin = changeAuthNextLogin;
        this.authExpiryDate = authExpiryDate;
        this.authAlgo = authAlgo;
        this.lastAuthChangeDate = lastAuthChangeDate;
        this.sessionTimeout = sessionTimeout;
    }

    public Integer getUserAccessCode() {
        return userAccessCode;
    }

    public void setUserAccessCode(Integer userAccessCode) {
        this.userAccessCode = userAccessCode;
    }

    public Integer getMultiFactorAuthEnabled() {
        return multiFactorAuthEnabled;
    }

    public void setMultiFactorAuthEnabled(Integer multiFactorAuthEnabled) {
        this.multiFactorAuthEnabled = multiFactorAuthEnabled;
    }

    public Integer getGenTempOneTimeAuth() {
        return genTempOneTimeAuth;
    }

    public void setGenTempOneTimeAuth(Integer genTempOneTimeAuth) {
        this.genTempOneTimeAuth = genTempOneTimeAuth;
    }

    public Integer getAllowMultipleLogin() {
        return allowMultipleLogin;
    }

    public void setAllowMultipleLogin(Integer allowMultipleLogin) {
        this.allowMultipleLogin = allowMultipleLogin;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getChangeAuthNextLogin() {
        return changeAuthNextLogin;
    }

    public void setChangeAuthNextLogin(Integer changeAuthNextLogin) {
        this.changeAuthNextLogin = changeAuthNextLogin;
    }

    public Timestamp getAuthExpiryDate() {
        return (authExpiryDate == null ? authExpiryDate : new Timestamp(authExpiryDate.getTime()));
    }

    public void setAuthExpiryDate(Timestamp authExpiryDate) {
        this.authExpiryDate = (authExpiryDate == null ? authExpiryDate : new Timestamp(authExpiryDate.getTime()));
    }

    public String getAuthAlgo() {
        return authAlgo;
    }

    public void setAuthAlgo(String authAlgo) {
        this.authAlgo = authAlgo;
    }

    public Timestamp getLastAuthChangeDate() {
        return (lastAuthChangeDate == null ? lastAuthChangeDate : new Timestamp(lastAuthChangeDate.getTime()));
    }

    public void setLastAuthChangeDate(Timestamp lastAuthChangeDate) {
        this.lastAuthChangeDate = (lastAuthChangeDate == null ? lastAuthChangeDate : new Timestamp(lastAuthChangeDate.getTime()));
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getPrimaryKey() {
        return userId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccessLevelId() {
        return userAccessLevelId;
    }

    public void setUserAccessLevelId(String userAccessLevelId) {
        this.userAccessLevelId = userAccessLevelId;
    }

    public String getUserAccessDomainId() {
        return userAccessDomainId;
    }

    public void setUserAccessDomainId(String userAccessDomainId) {
        this.userAccessDomainId = userAccessDomainId;
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

    public UserDetail addPassRecovery(PassRecovery passRecovery) {
        if (this.passRecovery == null) {
            this.passRecovery = new java.util.ArrayList<com.app.shared.appbasicsetup.usermanagement.PassRecovery>();
        }
        if (passRecovery != null) {
            passRecovery.setUserDetail(this);
            this.passRecovery.add(passRecovery);
        }
        return this;
    }

    public UserDetail removePassRecovery(PassRecovery passRecovery) {
        if (this.passRecovery != null) {
            this.passRecovery.remove(passRecovery);
        }
        return this;
    }

    public UserDetail addAllPassRecovery(List<PassRecovery> passRecovery) {
        if (this.passRecovery == null) {
            this.passRecovery = new java.util.ArrayList<com.app.shared.appbasicsetup.usermanagement.PassRecovery>();
        }
        if (passRecovery != null) {
            for (int i = 0; i < passRecovery.size(); i++) {
                passRecovery.get(i).setUserDetail(this);
            }
            this.passRecovery.addAll(passRecovery);
        }
        return this;
    }

    @JsonIgnore
    public Integer getTotalNumberOfPassRecovery() {
        int count = 0;
        if (this.passRecovery != null) {
            count = this.passRecovery.size();
        }
        return count;
    }

    public List<PassRecovery> getPassRecovery() {
        return passRecovery;
    }

    public void setPassRecovery(List<PassRecovery> passRecovery) {
        for (int i = 0; i < passRecovery.size(); i++) {
            if (passRecovery.get(i).getUserDetail() == null) {
                passRecovery.get(i).setUserDetail(this);
            }
        }
        this.passRecovery = passRecovery;
    }

    @JsonIgnore
    public List<PassRecovery> getDeletedPassRecoveryList() {
        List<PassRecovery> passrecoveryToRemove = new java.util.ArrayList<PassRecovery>();
        for (PassRecovery passrecovery : this.passRecovery) {
            if (passrecovery.isHardDelete()) {
                passrecoveryToRemove.add(passrecovery);
            }
        }
        passRecovery.removeAll(passrecoveryToRemove);
        return passrecoveryToRemove;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        if (userData.getUserDetail() == null) {
            userData.setUserDetail(this);
        }
        this.userData = userData;
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
        setValidatorpassRecovery(validateFactory);
    }

    private void setValidatorpassRecovery(EntityValidatorHelper<Object> validateFactory) {
        if (passRecovery != null) {
            for (int i = 0; i < passRecovery.size(); i++) {
                passRecovery.get(i).setEntityValidator(validateFactory);
            }
        }
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
        if (this.passRecovery == null) {
            this.passRecovery = new java.util.ArrayList<PassRecovery>();
        }
        for (PassRecovery passRecovery : this.passRecovery) {
            passRecovery.setEntityAudit(customerId, userId, recordType);
            passRecovery.setSystemInformation(recordType);
        }
        if (this.passRecovery == null) {
            this.passRecovery = new java.util.ArrayList<PassRecovery>();
        }
        for (PassRecovery passRecovery : this.passRecovery) {
            passRecovery.setEntityAudit(customerId, userId, recordType);
            passRecovery.setSystemInformation(recordType);
        }
        if (this.userData != null) {
            userData.setEntityAudit(customerId, userId, recordType);
            userData.setSystemInformation(recordType);
        }
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
        if (this.passRecovery == null) {
            this.passRecovery = new java.util.ArrayList<PassRecovery>();
        }
        for (PassRecovery passRecovery : this.passRecovery) {
            passRecovery.setEntityAudit(customerId, userId);
        }
        if (this.passRecovery == null) {
            this.passRecovery = new java.util.ArrayList<PassRecovery>();
        }
        for (PassRecovery passRecovery : this.passRecovery) {
            passRecovery.setEntityAudit(customerId, userId);
        }
        if (this.userData != null) {
            userData.setEntityAudit(customerId, userId);
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
            this.systemInfo.setActiveStatus(-1);
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

    public Integer getUseraccesslevelUserAccessLevel() {
        return useraccesslevelUserAccessLevel;
    }

    public void setUseraccesslevelUserAccessLevel(Integer useraccesslevelUserAccessLevel) {
        this.useraccesslevelUserAccessLevel = useraccesslevelUserAccessLevel;
    }

    public String getUseraccesslevelLevelName() {
        return useraccesslevelLevelName;
    }

    public void setUseraccesslevelLevelName(String useraccesslevelLevelName) {
        this.useraccesslevelLevelName = useraccesslevelLevelName;
    }

    public String getUseraccesslevelLevelDescription() {
        return useraccesslevelLevelDescription;
    }

    public void setUseraccesslevelLevelDescription(String useraccesslevelLevelDescription) {
        this.useraccesslevelLevelDescription = useraccesslevelLevelDescription;
    }

    public Integer getUseraccessdomainUserAccessDomain() {
        return useraccessdomainUserAccessDomain;
    }

    public void setUseraccessdomainUserAccessDomain(Integer useraccessdomainUserAccessDomain) {
        this.useraccessdomainUserAccessDomain = useraccessdomainUserAccessDomain;
    }

    public String getUseraccessdomainDomainName() {
        return useraccessdomainDomainName;
    }

    public void setUseraccessdomainDomainName(String useraccessdomainDomainName) {
        this.useraccessdomainDomainName = useraccessdomainDomainName;
    }

    public String getUseraccessdomainDomainDescription() {
        return useraccessdomainDomainDescription;
    }

    public void setUseraccessdomainDomainDescription(String useraccessdomainDomainDescription) {
        this.useraccessdomainDomainDescription = useraccessdomainDomainDescription;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param UserDetail
     * @param UserDetail
     */
    @Override
    public int compare(UserDetail object1, UserDetail object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((authAlgo == null ? " " : authAlgo));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (userId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = userId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.usermanagement.UserDetail other = (com.app.shared.appbasicsetup.usermanagement.UserDetail) obj;
            if (userId == null) {
                isEquals = false;
            } else if (!userId.equals(other.userId)) {
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

    public void setUserData(UserDataInterface userDataInterface) {
        this.userData = (UserData) userDataInterface;
    }
}
