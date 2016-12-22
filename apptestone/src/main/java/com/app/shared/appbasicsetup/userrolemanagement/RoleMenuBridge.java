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
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
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
import javax.persistence.NamedQueries;

@Table(name = "RoleMenuBridge")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "4", comments = "RoleMenuBridge", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "RoleMenuBridgeResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge.class, columns = { @javax.persistence.ColumnResult(name = "roleMenuMapId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "rolesRoleName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "menuId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "appmenusMenuLabel", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "isRead", type = java.lang.Boolean.class), @javax.persistence.ColumnResult(name = "isWrite", type = java.lang.Boolean.class), @javax.persistence.ColumnResult(name = "isExecute", type = java.lang.Boolean.class) }))
@NamedQueries({ @javax.persistence.NamedQuery(name = "RoleMenuBridge.findByMenuId", query = "select e from RoleMenuBridge e where e.systemInfo.activeStatus=1 and e.menuId=:menuId"), @javax.persistence.NamedQuery(name = "RoleMenuBridge.findByRoleId", query = "select e from RoleMenuBridge e where e.systemInfo.activeStatus=1 and e.roles.roleId=:roleId"), @javax.persistence.NamedQuery(name = "RoleMenuBridge.findById", query = "select e from RoleMenuBridge e where e.systemInfo.activeStatus=1 and e.roleMenuMapId =:roleMenuMapId") })
public class RoleMenuBridge implements Serializable, CommonEntityInterface, Comparator<RoleMenuBridge> {

    private static final long serialVersionUID = 1482410114000L;

    @Column(name = "isRead")
    @JsonProperty("isRead")
    @NotNull
    private Boolean isRead;

    @Column(name = "isWrite")
    @JsonProperty("isWrite")
    @NotNull
    private Boolean isWrite;

    @Column(name = "isExecute")
    @JsonProperty("isExecute")
    @NotNull
    private Boolean isExecute;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "roleMenuMapId")
    @JsonProperty("roleMenuMapId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 3, max = 64)
    private String roleMenuMapId;

    @Column(name = "menuId")
    @JsonProperty("menuId")
    private String menuId;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Roles roles;

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
    private String appmenusMenuLabel;

    public RoleMenuBridge() {
    }

    public RoleMenuBridge(String roleMenuMapId, String rolesRoleName, String menuId, String appmenusMenuLabel, Boolean isRead, Boolean isWrite, Boolean isExecute) {
        this.roleMenuMapId = roleMenuMapId;
        this.rolesRoleName = rolesRoleName;
        this.menuId = menuId;
        this.appmenusMenuLabel = appmenusMenuLabel;
        this.isRead = isRead;
        this.isWrite = isWrite;
        this.isExecute = isExecute;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        if (isRead != null) {
            this.isRead = isRead;
        }
    }

    public Boolean getIsWrite() {
        return isWrite;
    }

    public void setIsWrite(Boolean isWrite) {
        if (isWrite != null) {
            this.isWrite = isWrite;
        }
    }

    public Boolean getIsExecute() {
        return isExecute;
    }

    public void setIsExecute(Boolean isExecute) {
        if (isExecute != null) {
            this.isExecute = isExecute;
        }
    }

    public String getPrimaryKey() {
        return roleMenuMapId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return roleMenuMapId;
    }

    public String getRoleMenuMapId() {
        return roleMenuMapId;
    }

    public void setRoleMenuMapId(String roleMenuMapId) {
        this.roleMenuMapId = roleMenuMapId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
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

    public String getAppmenusMenuLabel() {
        return appmenusMenuLabel;
    }

    public void setAppmenusMenuLabel(String appmenusMenuLabel) {
        this.appmenusMenuLabel = appmenusMenuLabel;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((roles.getPrimaryDisplay().toString() == null ? " " : roles.getPrimaryDisplay().toString()) + ",").append((menuId.toString() == null ? " " : menuId.toString()) + ",").append((isRead == null ? " " : isRead) + ",").append((isWrite == null ? " " : isWrite) + ",").append((isExecute == null ? " " : isExecute));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (roleMenuMapId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = roleMenuMapId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge other = (com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge) obj;
            if (roleMenuMapId == null) {
                isEquals = false;
            } else if (!roleMenuMapId.equals(other.roleMenuMapId)) {
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

    @Transient
    @JsonIgnore
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String _fieldName) {
        this.fieldName = _fieldName;
    }

    @Override
    public int compare(RoleMenuBridge object1, RoleMenuBridge object2) {
        switch(((fieldName))) {
            case "roleMenuMapId":
                return (object1.getRoleMenuMapId().compareTo(object2.getRoleMenuMapId()) == 0) ? 0 : ((object1.getRoleMenuMapId().compareTo(object2.getRoleMenuMapId()) > 0) ? 1 : -1);
        }
        return 0;
    }
}
