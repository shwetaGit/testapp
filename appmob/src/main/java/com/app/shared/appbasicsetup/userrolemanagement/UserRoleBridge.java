package com.app.shared.appbasicsetup.userrolemanagement;
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
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import javax.persistence.SqlResultSetMapping;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "UserRoleBridge")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "UserRoleBridge", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "UserRoleBridgeResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge.class, columns = { @javax.persistence.ColumnResult(name = "roleUserMapId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "roleId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "rolesRoleName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "userId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "userdetailAuthAlgo", type = java.lang.String.class) }))
@NamedQueries({ @javax.persistence.NamedQuery(name = "UserRoleBridge.findAll", query = " select u from UserRoleBridge u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "UserRoleBridge.findByRoleId", query = "select e from UserRoleBridge e where e.systemInfo.activeStatus=1 and e.roleId=:roleId"), @javax.persistence.NamedQuery(name = "UserRoleBridge.findByUserId", query = "select e from UserRoleBridge e where e.systemInfo.activeStatus=1 and e.userId=:userId"), @javax.persistence.NamedQuery(name = "UserRoleBridge.findById", query = "select e from UserRoleBridge e where e.systemInfo.activeStatus=1 and e.roleUserMapId =:roleUserMapId") })
public class UserRoleBridge implements Serializable, CommonEntityInterface, Comparator<UserRoleBridge> {

    private static final long serialVersionUID = 1482410099088L;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "roleUserMapId")
    @JsonProperty("roleUserMapId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 3, max = 64)
    private String roleUserMapId;

    @Column(name = "roleId")
    @JsonProperty("roleId")
    private String roleId;

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
    private String rolesRoleName;

    @Transient
    private String userdetailAuthAlgo;

    public UserRoleBridge() {
    }

    public UserRoleBridge(String roleUserMapId, String roleId, String rolesRoleName, String userId, String userdetailAuthAlgo) {
        this.roleUserMapId = roleUserMapId;
        this.roleId = roleId;
        this.rolesRoleName = rolesRoleName;
        this.userId = userId;
        this.userdetailAuthAlgo = userdetailAuthAlgo;
    }

    public String getPrimaryKey() {
        return roleUserMapId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return roleUserMapId;
    }

    public String getRoleUserMapId() {
        return roleUserMapId;
    }

    public void setRoleUserMapId(String roleUserMapId) {
        this.roleUserMapId = roleUserMapId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public String getRolesRoleName() {
        return rolesRoleName;
    }

    public void setRolesRoleName(String rolesRoleName) {
        this.rolesRoleName = rolesRoleName;
    }

    public String getUserdetailAuthAlgo() {
        return userdetailAuthAlgo;
    }

    public void setUserdetailAuthAlgo(String userdetailAuthAlgo) {
        this.userdetailAuthAlgo = userdetailAuthAlgo;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param UserRoleBridge
     * @param UserRoleBridge
     */
    @Override
    public int compare(UserRoleBridge object1, UserRoleBridge object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((roleId.toString() == null ? " " : roleId.toString()) + ",").append((userId.toString() == null ? " " : userId.toString()));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (roleUserMapId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = roleUserMapId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge other = (com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge) obj;
            if (roleUserMapId == null) {
                isEquals = false;
            } else if (!roleUserMapId.equals(other.roleUserMapId)) {
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
