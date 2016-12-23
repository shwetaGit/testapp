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
import javax.validation.constraints.Min;
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

@Table(name = "City")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "City", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "CityResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.organization.locationmanagement.City.class, columns = { @javax.persistence.ColumnResult(name = "cityId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "countryId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "countryCountryName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateStateName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "cityName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "cityCodeChar2", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "cityCode", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "cityDescription", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "cityFlag", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "cityLatitude", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "cityLongitude", type = java.lang.Integer.class) }))
@NamedQueries({ @javax.persistence.NamedQuery(name = "City.findAll", query = " select u from City u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "City.findByCountryId", query = "select e from City e where e.systemInfo.activeStatus=1 and e.countryId=:countryId"), @javax.persistence.NamedQuery(name = "City.findByStateId", query = "select e from City e where e.systemInfo.activeStatus=1 and e.stateId=:stateId"), @javax.persistence.NamedQuery(name = "City.findById", query = "select e from City e where e.systemInfo.activeStatus=1 and e.cityId =:cityId") })
public class City implements Serializable, CommonEntityInterface, Comparator<City> {

    private static final long serialVersionUID = 1482410074045L;

    @Column(name = "cityName")
    @JsonProperty("cityName")
    @NotNull
    @Size(min = 0, max = 256)
    private String cityName;

    @Column(name = "cityCodeChar2")
    @JsonProperty("cityCodeChar2")
    @NotNull
    @Size(min = 0, max = 32)
    private String cityCodeChar2;

    @Column(name = "cityCode")
    @JsonProperty("cityCode")
    @NotNull
    @Min(0)
    @Max(3)
    private Integer cityCode;

    @Column(name = "cityDescription")
    @JsonProperty("cityDescription")
    @Size(max = 128)
    private String cityDescription;

    @Column(name = "cityFlag")
    @JsonProperty("cityFlag")
    @Size(max = 128)
    private String cityFlag;

    @Column(name = "cityLatitude")
    @JsonProperty("cityLatitude")
    @Max(11)
    private Integer cityLatitude;

    @Column(name = "cityLongitude")
    @JsonProperty("cityLongitude")
    @Max(11)
    private Integer cityLongitude;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "cityId")
    @JsonProperty("cityId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String cityId;

    @Column(name = "countryId")
    @JsonProperty("countryId")
    private String countryId;

    @Column(name = "stateId")
    @JsonProperty("stateId")
    private String stateId;

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

    @Transient
    private String stateStateName;

    public City() {
    }

    public City(String cityId, String countryId, String countryCountryName, String stateId, String stateStateName, String cityName, String cityCodeChar2, Integer cityCode, String cityDescription, String cityFlag, Integer cityLatitude, Integer cityLongitude) {
        this.cityId = cityId;
        this.countryId = countryId;
        this.countryCountryName = countryCountryName;
        this.stateId = stateId;
        this.stateStateName = stateStateName;
        this.cityName = cityName;
        this.cityCodeChar2 = cityCodeChar2;
        this.cityCode = cityCode;
        this.cityDescription = cityDescription;
        this.cityFlag = cityFlag;
        this.cityLatitude = cityLatitude;
        this.cityLongitude = cityLongitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        if (cityName != null) {
            this.cityName = cityName;
        }
    }

    public String getCityCodeChar2() {
        return cityCodeChar2;
    }

    public void setCityCodeChar2(String cityCodeChar2) {
        if (cityCodeChar2 != null) {
            this.cityCodeChar2 = cityCodeChar2;
        }
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        if (cityCode != null) {
            this.cityCode = cityCode;
        }
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    public String getCityFlag() {
        return cityFlag;
    }

    public void setCityFlag(String cityFlag) {
        this.cityFlag = cityFlag;
    }

    public Integer getCityLatitude() {
        return cityLatitude;
    }

    public void setCityLatitude(Integer cityLatitude) {
        this.cityLatitude = cityLatitude;
    }

    public Integer getCityLongitude() {
        return cityLongitude;
    }

    public void setCityLongitude(Integer cityLongitude) {
        this.cityLongitude = cityLongitude;
    }

    public String getPrimaryKey() {
        return cityId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
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

    public String getStateStateName() {
        return stateStateName;
    }

    public void setStateStateName(String stateStateName) {
        this.stateStateName = stateStateName;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param City
     * @param City
     */
    @Override
    public int compare(City object1, City object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((cityName == null ? " " : cityName) + ",").append((cityCodeChar2 == null ? " " : cityCodeChar2) + ",").append((cityCode == null ? " " : cityCode));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (cityId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = cityId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.organization.locationmanagement.City other = (com.app.shared.organization.locationmanagement.City) obj;
            if (cityId == null) {
                isEquals = false;
            } else if (!cityId.equals(other.cityId)) {
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
