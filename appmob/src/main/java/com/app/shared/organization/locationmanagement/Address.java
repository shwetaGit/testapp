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
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
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

@Table(name = "Address")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Address", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "AddressResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.organization.locationmanagement.Address.class, columns = { @javax.persistence.ColumnResult(name = "addressId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "addressTypeId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "addresstypeAddressType", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "addressLabel", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "address1", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "address2", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "address3", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "countryId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "countryCountryName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "stateStateName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "cityId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "cityCityName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "cityCityCodeChar2", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "cityCityCode", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "zipcode", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "latitude", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "longitude", type = java.lang.String.class) }))
@NamedQueries({ @javax.persistence.NamedQuery(name = "Address.findAll", query = " select u from Address u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Address.DefaultFinders", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.addressTypeId LIKE :addressTypeId"), @javax.persistence.NamedQuery(name = "Address.findByAddressTypeId", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.addressTypeId=:addressTypeId"), @javax.persistence.NamedQuery(name = "Address.findByCountryId", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.countryId=:countryId"), @javax.persistence.NamedQuery(name = "Address.findByStateId", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.stateId=:stateId"), @javax.persistence.NamedQuery(name = "Address.findByCityId", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.cityId=:cityId"), @javax.persistence.NamedQuery(name = "Address.findById", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.addressId =:addressId") })
public class Address implements Serializable, CommonEntityInterface, Comparator<Address> {

    private static final long serialVersionUID = 1482410075603L;

    @Column(name = "addressLabel")
    @JsonProperty("addressLabel")
    @Size(max = 11)
    private String addressLabel;

    @Column(name = "address1")
    @JsonProperty("address1")
    @Size(max = 56)
    private String address1;

    @Column(name = "address2")
    @JsonProperty("address2")
    @Size(max = 56)
    private String address2;

    @Column(name = "address3")
    @JsonProperty("address3")
    @Size(max = 56)
    private String address3;

    @Column(name = "zipcode")
    @JsonProperty("zipcode")
    @NotNull
    @Size(min = 0, max = 6)
    private String zipcode;

    @Column(name = "latitude")
    @JsonProperty("latitude")
    @Size(max = 64)
    private String latitude;

    @Column(name = "longitude")
    @JsonProperty("longitude")
    @Size(max = 64)
    private String longitude;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "addressId")
    @JsonProperty("addressId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String addressId;

    @Column(name = "addressTypeId")
    @JsonProperty("addressTypeId")
    private String addressTypeId;

    @Column(name = "countryId")
    @JsonProperty("countryId")
    private String countryId;

    @Column(name = "stateId")
    @JsonProperty("stateId")
    private String stateId;

    @Column(name = "cityId")
    @JsonProperty("cityId")
    private String cityId;

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
    private String addresstypeAddressType;

    @Transient
    private String countryCountryName;

    @Transient
    private String stateStateName;

    @Transient
    private String cityCityName;

    @Transient
    private String cityCityCodeChar2;

    @Transient
    private Integer cityCityCode;

    public Address() {
    }

    public Address(String addressId, String addressTypeId, String addresstypeAddressType, String addressLabel, String address1, String address2, String address3, String countryId, String countryCountryName, String stateId, String stateStateName, String cityId, String cityCityName, String cityCityCodeChar2, Integer cityCityCode, String zipcode, String latitude, String longitude) {
        this.addressId = addressId;
        this.addressTypeId = addressTypeId;
        this.addresstypeAddressType = addresstypeAddressType;
        this.addressLabel = addressLabel;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.countryId = countryId;
        this.countryCountryName = countryCountryName;
        this.stateId = stateId;
        this.stateStateName = stateStateName;
        this.cityId = cityId;
        this.cityCityName = cityCityName;
        this.cityCityCodeChar2 = cityCityCodeChar2;
        this.cityCityCode = cityCityCode;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(String addressLabel) {
        this.addressLabel = addressLabel;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        if (zipcode != null) {
            this.zipcode = zipcode;
        }
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrimaryKey() {
        return addressId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return addressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressTypeId() {
        return addressTypeId;
    }

    public void setAddressTypeId(String addressTypeId) {
        this.addressTypeId = addressTypeId;
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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

    public String getAddresstypeAddressType() {
        return addresstypeAddressType;
    }

    public void setAddresstypeAddressType(String addresstypeAddressType) {
        this.addresstypeAddressType = addresstypeAddressType;
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

    public String getCityCityName() {
        return cityCityName;
    }

    public void setCityCityName(String cityCityName) {
        this.cityCityName = cityCityName;
    }

    public String getCityCityCodeChar2() {
        return cityCityCodeChar2;
    }

    public void setCityCityCodeChar2(String cityCityCodeChar2) {
        this.cityCityCodeChar2 = cityCityCodeChar2;
    }

    public Integer getCityCityCode() {
        return cityCityCode;
    }

    public void setCityCityCode(Integer cityCityCode) {
        this.cityCityCode = cityCityCode;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param Address
     * @param Address
     */
    @Override
    public int compare(Address object1, Address object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((zipcode == null ? " " : zipcode));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (addressId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = addressId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.organization.locationmanagement.Address other = (com.app.shared.organization.locationmanagement.Address) obj;
            if (addressId == null) {
                isEquals = false;
            } else if (!addressId.equals(other.addressId)) {
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
