package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.server.interfaces.UserDataInterface;
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
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import javax.persistence.SqlResultSetMapping;
import java.lang.Override;
import com.spartan.server.interfaces.UserInterface;
import javax.persistence.NamedQueries;

@Table(name = "UserData")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "UserData", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "UserDataResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.appbasicsetup.usermanagement.UserData.class, columns = { @javax.persistence.ColumnResult(name = "userDataId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "userdetailAuthAlgo", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "password", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "oneTimePassword", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "oneTimePasswordExpiry", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "oneTimePasswordGenDate", type = java.sql.Timestamp.class), @javax.persistence.ColumnResult(name = "last5Passwords", type = java.lang.String.class) }))
@NamedQueries({ @javax.persistence.NamedQuery(name = "UserData.findByUserId", query = "select e from UserData e where e.systemInfo.activeStatus=1 and e.userDetail.userId=:userId"), @javax.persistence.NamedQuery(name = "UserData.findById", query = "select e from UserData e where e.systemInfo.activeStatus=1 and e.userDataId =:userDataId") })
public class UserData implements Serializable, CommonEntityInterface, UserDataInterface, Comparator<UserData> {

    private static final long serialVersionUID = 1482410095886L;

    @Column(name = "password")
    @JsonProperty("password")
    @NotNull
    @Size(min = 0, max = 512)
    private String password;

    @Column(name = "oneTimePassword")
    @JsonProperty("oneTimePassword")
    @Size(max = 32)
    private String oneTimePassword;

    @Column(name = "oneTimePasswordExpiry")
    @JsonProperty("oneTimePasswordExpiry")
    @Max(11)
    private Integer oneTimePasswordExpiry;

    @Column(name = "oneTimePasswordGenDate")
    @JsonProperty("oneTimePasswordGenDate")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp oneTimePasswordGenDate;

    @Column(name = "last5Passwords")
    @JsonProperty("last5Passwords")
    @Size(max = 5120)
    private String last5Passwords;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "userDataId")
    @JsonProperty("userDataId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String userDataId;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserDetail userDetail;

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

    public UserData() {
    }

    public UserData(String userDataId, String userdetailAuthAlgo, String password, String oneTimePassword, Integer oneTimePasswordExpiry, Timestamp oneTimePasswordGenDate, String last5Passwords) {
        this.userDataId = userDataId;
        this.userdetailAuthAlgo = userdetailAuthAlgo;
        this.password = password;
        this.oneTimePassword = oneTimePassword;
        this.oneTimePasswordExpiry = oneTimePasswordExpiry;
        this.oneTimePasswordGenDate = oneTimePasswordGenDate;
        this.last5Passwords = last5Passwords;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null) {
            this.password = password;
        }
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public Integer getOneTimePasswordExpiry() {
        return oneTimePasswordExpiry;
    }

    public void setOneTimePasswordExpiry(Integer oneTimePasswordExpiry) {
        this.oneTimePasswordExpiry = oneTimePasswordExpiry;
    }

    public Timestamp getOneTimePasswordGenDate() {
        return (oneTimePasswordGenDate == null ? oneTimePasswordGenDate : new Timestamp(oneTimePasswordGenDate.getTime()));
    }

    public void setOneTimePasswordGenDate(Timestamp oneTimePasswordGenDate) {
        this.oneTimePasswordGenDate = (oneTimePasswordGenDate == null ? oneTimePasswordGenDate : new Timestamp(oneTimePasswordGenDate.getTime()));
    }

    public String getLast5Passwords() {
        return last5Passwords;
    }

    public void setLast5Passwords(String last5Passwords) {
        this.last5Passwords = last5Passwords;
    }

    public String getPrimaryKey() {
        return userDataId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return userDataId;
    }

    public String getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
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

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
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
     * @param UserData
     * @param UserData
     */
    @Override
    public int compare(UserData object1, UserData object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((userDetail.getPrimaryDisplay().toString() == null ? " " : userDetail.getPrimaryDisplay().toString()));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (userDataId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = userDataId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.usermanagement.UserData other = (com.app.shared.appbasicsetup.usermanagement.UserData) obj;
            if (userDataId == null) {
                isEquals = false;
            } else if (!userDataId.equals(other.userDataId)) {
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

    public void setUserDetail(UserInterface userInterface) {
        this.userDetail = (UserDetail) userInterface;
    }
}
