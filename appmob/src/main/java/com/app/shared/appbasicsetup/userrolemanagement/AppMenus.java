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

@Table(name = "AppMenus")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "AppMenus", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "AppMenus.findAll", query = " select u from AppMenus u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "AppMenus.DefaultFinders", query = "select e from AppMenus e where e.systemInfo.activeStatus=1 and e.menuAccessRights BETWEEN :minmenuAccessRights AND :maxmenuAccessRights"), @javax.persistence.NamedQuery(name = "AppMenus.findById", query = "select e from AppMenus e where e.systemInfo.activeStatus=1 and e.menuId =:menuId") })
public class AppMenus implements Serializable, CommonEntityInterface, Comparator<AppMenus> {

    private static final long serialVersionUID = 1482410098054L;

    @Column(name = "menuTreeId")
    @JsonProperty("menuTreeId")
    @NotNull
    @Size(min = 2, max = 256)
    private String menuTreeId;

    @Column(name = "menuIcon")
    @JsonProperty("menuIcon")
    @Size(max = 256)
    private String menuIcon;

    @Column(name = "menuAction")
    @JsonProperty("menuAction")
    @Size(max = 256)
    private String menuAction;

    @Column(name = "menuCommands")
    @JsonProperty("menuCommands")
    @Size(max = 64)
    private String menuCommands;

    @Column(name = "menuDisplay")
    @JsonProperty("menuDisplay")
    @NotNull
    private Boolean menuDisplay;

    @Column(name = "menuHead")
    @JsonProperty("menuHead")
    @NotNull
    private Boolean menuHead;

    @Column(name = "menuLabel")
    @JsonProperty("menuLabel")
    @NotNull
    @Size(min = 0, max = 256)
    private String menuLabel;

    @Column(name = "uiType")
    @JsonProperty("uiType")
    @Size(max = 3)
    private String uiType;

    @Column(name = "RefObjectId")
    @JsonProperty("refObjectId")
    @Size(max = 256)
    private String refObjectId;

    @Column(name = "menuAccessRights")
    @JsonProperty("menuAccessRights")
    @NotNull
    @Min(0)
    @Max(11)
    private Integer menuAccessRights;

    @Column(name = "appType")
    @JsonProperty("appType")
    @Max(2)
    private Integer appType;

    @Column(name = "appId")
    @JsonProperty("appId")
    @Size(max = 256)
    private String appId;

    @Column(name = "autoSave")
    @JsonProperty("autoSave")
    @NotNull
    private Boolean autoSave;

    @Column(name = "startDate")
    @JsonProperty("startDate")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp startDate;

    @Column(name = "expiryDate")
    @JsonProperty("expiryDate")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp expiryDate;

    @Column(name = "goLiveDate")
    @JsonProperty("goLiveDate")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp goLiveDate;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "menuId")
    @JsonProperty("menuId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 3, max = 64)
    private String menuId;

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

    public String getMenuTreeId() {
        return menuTreeId;
    }

    public void setMenuTreeId(String menuTreeId) {
        if (menuTreeId != null) {
            this.menuTreeId = menuTreeId;
        }
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    public String getMenuCommands() {
        return menuCommands;
    }

    public void setMenuCommands(String menuCommands) {
        this.menuCommands = menuCommands;
    }

    public Boolean getMenuDisplay() {
        return menuDisplay;
    }

    public void setMenuDisplay(Boolean menuDisplay) {
        if (menuDisplay != null) {
            this.menuDisplay = menuDisplay;
        }
    }

    public Boolean getMenuHead() {
        return menuHead;
    }

    public void setMenuHead(Boolean menuHead) {
        if (menuHead != null) {
            this.menuHead = menuHead;
        }
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        if (menuLabel != null) {
            this.menuLabel = menuLabel;
        }
    }

    public String getUiType() {
        return uiType;
    }

    public void setUiType(String uiType) {
        this.uiType = uiType;
    }

    public String getRefObjectId() {
        return refObjectId;
    }

    public void setRefObjectId(String refObjectId) {
        this.refObjectId = refObjectId;
    }

    public Integer getMenuAccessRights() {
        return menuAccessRights;
    }

    public void setMenuAccessRights(Integer menuAccessRights) {
        if (menuAccessRights != null) {
            this.menuAccessRights = menuAccessRights;
        }
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Boolean getAutoSave() {
        return autoSave;
    }

    public void setAutoSave(Boolean autoSave) {
        if (autoSave != null) {
            this.autoSave = autoSave;
        }
    }

    public Timestamp getStartDate() {
        return (startDate == null ? startDate : new Timestamp(startDate.getTime()));
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = (startDate == null ? startDate : new Timestamp(startDate.getTime()));
    }

    public Timestamp getExpiryDate() {
        return (expiryDate == null ? expiryDate : new Timestamp(expiryDate.getTime()));
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = (expiryDate == null ? expiryDate : new Timestamp(expiryDate.getTime()));
    }

    public Timestamp getGoLiveDate() {
        return (goLiveDate == null ? goLiveDate : new Timestamp(goLiveDate.getTime()));
    }

    public void setGoLiveDate(Timestamp goLiveDate) {
        this.goLiveDate = (goLiveDate == null ? goLiveDate : new Timestamp(goLiveDate.getTime()));
    }

    public String getPrimaryKey() {
        return menuId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return menuId;
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
     * @param AppMenus
     * @param AppMenus
     */
    @Override
    public int compare(AppMenus object1, AppMenus object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((menuLabel == null ? " " : menuLabel));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (menuId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = menuId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.userrolemanagement.AppMenus other = (com.app.shared.appbasicsetup.userrolemanagement.AppMenus) obj;
            if (menuId == null) {
                isEquals = false;
            } else if (!menuId.equals(other.menuId)) {
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
