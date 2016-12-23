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
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "Country")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Country", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Country.findAll", query = " select u from Country u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Country.DefaultFinders", query = "select e from Country e where e.systemInfo.activeStatus=1 and e.countryName LIKE :countryName and e.countryCode1 LIKE :countryCode1"), @javax.persistence.NamedQuery(name = "Country.findById", query = "select e from Country e where e.systemInfo.activeStatus=1 and e.countryId =:countryId") })
public class Country implements Serializable, CommonEntityInterface, Comparator<Country> {

    private static final long serialVersionUID = 1482410072778L;

    @Column(name = "countryName")
    @JsonProperty("countryName")
    @NotNull
    @Size(min = 0, max = 128)
    private String countryName;

    @Column(name = "countryCode1")
    @JsonProperty("countryCode1")
    @Size(max = 3)
    private String countryCode1;

    @Column(name = "countryCode2")
    @JsonProperty("countryCode2")
    @Size(max = 3)
    private String countryCode2;

    @Column(name = "countryFlag")
    @JsonProperty("countryFlag")
    @Size(max = 64)
    private String countryFlag;

    @Column(name = "capital")
    @JsonProperty("capital")
    @Size(max = 32)
    private String capital;

    @Column(name = "currencyCode")
    @JsonProperty("currencyCode")
    @Size(max = 3)
    private String currencyCode;

    @Column(name = "currencyName")
    @JsonProperty("currencyName")
    @Size(max = 128)
    private String currencyName;

    @Column(name = "currencySymbol")
    @JsonProperty("currencySymbol")
    @Size(max = 32)
    private String currencySymbol;

    @Column(name = "capitalLatitude")
    @JsonProperty("capitalLatitude")
    @Max(11)
    private Integer capitalLatitude;

    @Column(name = "capitalLongitude")
    @JsonProperty("capitalLongitude")
    @Max(11)
    private Integer capitalLongitude;

    @Column(name = "isoNumeric")
    @JsonProperty("isoNumeric")
    @Max(1000)
    private Integer isoNumeric;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "countryId")
    @JsonProperty("countryId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        if (countryName != null) {
            this.countryName = countryName;
        }
    }

    public String getCountryCode1() {
        return countryCode1;
    }

    public void setCountryCode1(String countryCode1) {
        this.countryCode1 = countryCode1;
    }

    public String getCountryCode2() {
        return countryCode2;
    }

    public void setCountryCode2(String countryCode2) {
        this.countryCode2 = countryCode2;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public Integer getCapitalLatitude() {
        return capitalLatitude;
    }

    public void setCapitalLatitude(Integer capitalLatitude) {
        this.capitalLatitude = capitalLatitude;
    }

    public Integer getCapitalLongitude() {
        return capitalLongitude;
    }

    public void setCapitalLongitude(Integer capitalLongitude) {
        this.capitalLongitude = capitalLongitude;
    }

    public Integer getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(Integer isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    public String getPrimaryKey() {
        return countryId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return countryId;
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

    /**
     * Compares 2 objects and returns integer value
     * @param Country
     * @param Country
     */
    @Override
    public int compare(Country object1, Country object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((countryName == null ? " " : countryName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (countryId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = countryId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.organization.locationmanagement.Country other = (com.app.shared.organization.locationmanagement.Country) obj;
            if (countryId == null) {
                isEquals = false;
            } else if (!countryId.equals(other.countryId)) {
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
