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
import javax.validation.constraints.Size;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "Roles")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Roles", complexity = Complexity.LOW)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "roleId")
@NamedQueries({ @javax.persistence.NamedQuery(name = "Roles.findAll", query = " select u from Roles u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Roles.findById", query = "select e from Roles e where e.systemInfo.activeStatus=1 and e.roleId =:roleId") })
public class Roles implements Serializable, CommonEntityInterface, Comparator<Roles> {

    private static final long serialVersionUID = 1482410112156L;

    @Column(name = "RoleName")
    @JsonProperty("roleName")
    @NotNull
    @Size(min = 0, max = 256)
    private String roleName;

    @Column(name = "RoleDescription")
    @JsonProperty("roleDescription")
    @NotNull
    @Size(min = 0, max = 256)
    private String roleDescription;

    @Column(name = "RoleIcon")
    @JsonProperty("roleIcon")
    @Size(max = 64)
    private String roleIcon;

    @Column(name = "roleHelp")
    @JsonProperty("roleHelp")
    @Size(max = 256)
    private String roleHelp;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "roleId")
    @JsonProperty("roleId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 3, max = 64)
    private String roleId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "roles")
    private List<RoleMenuBridge> roleMenuBridge;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        if (roleName != null) {
            this.roleName = roleName;
        }
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        if (roleDescription != null) {
            this.roleDescription = roleDescription;
        }
    }

    public String getRoleIcon() {
        return roleIcon;
    }

    public void setRoleIcon(String roleIcon) {
        this.roleIcon = roleIcon;
    }

    public String getRoleHelp() {
        return roleHelp;
    }

    public void setRoleHelp(String roleHelp) {
        this.roleHelp = roleHelp;
    }

    public String getPrimaryKey() {
        return roleId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public Roles addRoleMenuBridge(RoleMenuBridge roleMenuBridge) {
        if (this.roleMenuBridge == null) {
            this.roleMenuBridge = new java.util.ArrayList<com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge>();
        }
        if (roleMenuBridge != null) {
            roleMenuBridge.setRoles(this);
            this.roleMenuBridge.add(roleMenuBridge);
        }
        return this;
    }

    public Roles removeRoleMenuBridge(RoleMenuBridge roleMenuBridge) {
        if (this.roleMenuBridge != null) {
            this.roleMenuBridge.remove(roleMenuBridge);
        }
        return this;
    }

    public Roles addAllRoleMenuBridge(List<RoleMenuBridge> roleMenuBridge) {
        if (this.roleMenuBridge == null) {
            this.roleMenuBridge = new java.util.ArrayList<com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge>();
        }
        if (roleMenuBridge != null) {
            for (int i = 0; i < roleMenuBridge.size(); i++) {
                roleMenuBridge.get(i).setRoles(this);
            }
            this.roleMenuBridge.addAll(roleMenuBridge);
        }
        return this;
    }

    @JsonIgnore
    public Integer getTotalNumberOfRoleMenuBridge() {
        int count = 0;
        if (this.roleMenuBridge != null) {
            count = this.roleMenuBridge.size();
        }
        return count;
    }

    public List<RoleMenuBridge> getRoleMenuBridge() {
        return roleMenuBridge;
    }

    public void setRoleMenuBridge(List<RoleMenuBridge> roleMenuBridge) {
        for (int i = 0; i < roleMenuBridge.size(); i++) {
            if (roleMenuBridge.get(i).getRoles() == null) {
                roleMenuBridge.get(i).setRoles(this);
            }
        }
        this.roleMenuBridge = roleMenuBridge;
    }

    @JsonIgnore
    public List<RoleMenuBridge> getDeletedRoleMenuBridgeList() {
        List<RoleMenuBridge> rolemenubridgeToRemove = new java.util.ArrayList<RoleMenuBridge>();
        for (RoleMenuBridge rolemenubridge : this.roleMenuBridge) {
            if (rolemenubridge.isHardDelete()) {
                rolemenubridgeToRemove.add(rolemenubridge);
            }
        }
        roleMenuBridge.removeAll(rolemenubridgeToRemove);
        return rolemenubridgeToRemove;
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
        setValidatorroleMenuBridge(validateFactory);
    }

    private void setValidatorroleMenuBridge(EntityValidatorHelper<Object> validateFactory) {
        if (roleMenuBridge != null) {
            for (int i = 0; i < roleMenuBridge.size(); i++) {
                roleMenuBridge.get(i).setEntityValidator(validateFactory);
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
        if (this.roleMenuBridge == null) {
            this.roleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        }
        for (RoleMenuBridge roleMenuBridge : this.roleMenuBridge) {
            roleMenuBridge.setEntityAudit(customerId, userId, recordType);
            roleMenuBridge.setSystemInformation(recordType);
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
        if (this.roleMenuBridge == null) {
            this.roleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        }
        for (RoleMenuBridge roleMenuBridge : this.roleMenuBridge) {
            roleMenuBridge.setEntityAudit(customerId, userId);
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

    /**
     * Compares 2 objects and returns integer value
     * @param Roles
     * @param Roles
     */
    @Override
    public int compare(Roles object1, Roles object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((roleName == null ? " " : roleName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (roleId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = roleId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.userrolemanagement.Roles other = (com.app.shared.appbasicsetup.userrolemanagement.Roles) obj;
            if (roleId == null) {
                isEquals = false;
            } else if (!roleId.equals(other.roleId)) {
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
