package com.app.shared.organization.contactmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.server.interfaces.CoreContactsInterface;
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
import com.app.shared.organization.locationmanagement.Address;
import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import com.app.shared.organization.locationmanagement.Timezone;
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

@Table(name = "CoreContacts")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "CoreContacts", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "CoreContactsResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.organization.contactmanagement.CoreContacts.class, columns = { @javax.persistence.ColumnResult(name = "contactId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "titleId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "titleTitles", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "firstName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "middleName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "lastName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "nativeLanguageCode", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "languageLanguage", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "nativeTitle", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "nativeFirstName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "nativeMiddleName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "nativeLastName", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "genderId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "genderGender", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "dateofbirth", type = java.sql.Timestamp.class), @javax.persistence.ColumnResult(name = "age", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "approximateDOB", type = java.sql.Timestamp.class), @javax.persistence.ColumnResult(name = "emailId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "phoneNumber", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "timezoneUtcdifference", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "timezoneTimeZoneLabel", type = java.lang.String.class) }))
@NamedQueries({ @javax.persistence.NamedQuery(name = "CoreContacts.findAll", query = " select u from CoreContacts u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "CoreContacts.findByTitleId", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.titleId=:titleId"), @javax.persistence.NamedQuery(name = "CoreContacts.findByNativeLanguageCode", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.nativeLanguageCode=:nativeLanguageCode"), @javax.persistence.NamedQuery(name = "CoreContacts.findByGenderId", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.genderId=:genderId"), @javax.persistence.NamedQuery(name = "CoreContacts.findByTimeZoneId", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.timezone.timeZoneId=:timeZoneId"), @javax.persistence.NamedQuery(name = "CoreContacts.findById", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.contactId =:contactId") })
public class CoreContacts implements Serializable, CommonEntityInterface, CoreContactsInterface, Comparator<CoreContacts> {

    private static final long serialVersionUID = 1482410094273L;

    @Column(name = "firstName")
    @JsonProperty("firstName")
    @NotNull
    @Size(min = 0, max = 256)
    private String firstName;

    @Column(name = "middleName")
    @JsonProperty("middleName")
    @Size(max = 256)
    private String middleName;

    @Column(name = "lastName")
    @JsonProperty("lastName")
    @NotNull
    @Size(min = 0, max = 256)
    private String lastName;

    @Column(name = "nativeTitle")
    @JsonProperty("nativeTitle")
    @Size(max = 64)
    private String nativeTitle;

    @Column(name = "nativeFirstName")
    @JsonProperty("nativeFirstName")
    @Size(max = 256)
    private String nativeFirstName;

    @Column(name = "nativeMiddleName")
    @JsonProperty("nativeMiddleName")
    @Size(max = 256)
    private String nativeMiddleName;

    @Column(name = "nativeLastName")
    @JsonProperty("nativeLastName")
    @Size(max = 256)
    private String nativeLastName;

    @Column(name = "dateofbirth")
    @JsonProperty("dateofbirth")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp dateofbirth;

    @Column(name = "age")
    @JsonProperty("age")
    @Max(125)
    private Integer age;

    @Column(name = "approximateDOB")
    @JsonProperty("approximateDOB")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp approximateDOB;

    @Column(name = "emailId")
    @JsonProperty("emailId")
    @NotNull
    @Size(min = 0, max = 256)
    private String emailId;

    @Column(name = "phoneNumber")
    @JsonProperty("phoneNumber")
    @NotNull
    @Size(min = 0, max = 20)
    private String phoneNumber;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "contactId")
    @JsonProperty("contactId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String contactId;

    @Column(name = "titleId")
    @JsonProperty("titleId")
    private String titleId;

    @Column(name = "nativeLanguageCode")
    @JsonProperty("nativeLanguageCode")
    private String nativeLanguageCode;

    @Column(name = "genderId")
    @JsonProperty("genderId")
    private String genderId;

    @JoinTable(name = "AddressMap", joinColumns = { @javax.persistence.JoinColumn(name = "contactId", referencedColumnName = "contactId") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "addressId", referencedColumnName = "addressId") })
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "timeZoneId", referencedColumnName = "timeZoneId")
    private Timezone timezone;

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
    private String titleTitles;

    @Transient
    private String languageLanguage;

    @Transient
    private String genderGender;

    @Transient
    private Integer timezoneUtcdifference;

    @Transient
    private String timezoneTimeZoneLabel;

    public CoreContacts() {
    }

    public CoreContacts(String contactId, String titleId, String titleTitles, String firstName, String middleName, String lastName, String nativeLanguageCode, String languageLanguage, String nativeTitle, String nativeFirstName, String nativeMiddleName, String nativeLastName, String genderId, String genderGender, Timestamp dateofbirth, Integer age, Timestamp approximateDOB, String emailId, String phoneNumber, Integer timezoneUtcdifference, String timezoneTimeZoneLabel) {
        this.contactId = contactId;
        this.titleId = titleId;
        this.titleTitles = titleTitles;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nativeLanguageCode = nativeLanguageCode;
        this.languageLanguage = languageLanguage;
        this.nativeTitle = nativeTitle;
        this.nativeFirstName = nativeFirstName;
        this.nativeMiddleName = nativeMiddleName;
        this.nativeLastName = nativeLastName;
        this.genderId = genderId;
        this.genderGender = genderGender;
        this.dateofbirth = dateofbirth;
        this.age = age;
        this.approximateDOB = approximateDOB;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.timezoneUtcdifference = timezoneUtcdifference;
        this.timezoneTimeZoneLabel = timezoneTimeZoneLabel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        }
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        }
    }

    public String getNativeTitle() {
        return nativeTitle;
    }

    public void setNativeTitle(String nativeTitle) {
        this.nativeTitle = nativeTitle;
    }

    public String getNativeFirstName() {
        return nativeFirstName;
    }

    public void setNativeFirstName(String nativeFirstName) {
        this.nativeFirstName = nativeFirstName;
    }

    public String getNativeMiddleName() {
        return nativeMiddleName;
    }

    public void setNativeMiddleName(String nativeMiddleName) {
        this.nativeMiddleName = nativeMiddleName;
    }

    public String getNativeLastName() {
        return nativeLastName;
    }

    public void setNativeLastName(String nativeLastName) {
        this.nativeLastName = nativeLastName;
    }

    public Timestamp getDateofbirth() {
        return (dateofbirth == null ? dateofbirth : new Timestamp(dateofbirth.getTime()));
    }

    public void setDateofbirth(Timestamp dateofbirth) {
        this.dateofbirth = (dateofbirth == null ? dateofbirth : new Timestamp(dateofbirth.getTime()));
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Timestamp getApproximateDOB() {
        return (approximateDOB == null ? approximateDOB : new Timestamp(approximateDOB.getTime()));
    }

    public void setApproximateDOB(Timestamp approximateDOB) {
        this.approximateDOB = (approximateDOB == null ? approximateDOB : new Timestamp(approximateDOB.getTime()));
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        if (emailId != null) {
            this.emailId = emailId;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getPrimaryKey() {
        return contactId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return contactId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getNativeLanguageCode() {
        return nativeLanguageCode;
    }

    public void setNativeLanguageCode(String nativeLanguageCode) {
        this.nativeLanguageCode = nativeLanguageCode;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
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

    public CoreContacts addAddress(Address address) {
        if (this.address == null) {
            this.address = new java.util.ArrayList<com.app.shared.organization.locationmanagement.Address>();
        }
        if (address != null) {
            this.address.add(address);
        }
        return this;
    }

    public CoreContacts removeAddress(Address address) {
        if (this.address != null) {
            this.address.remove(address);
        }
        return this;
    }

    public CoreContacts addAllAddress(List<Address> address) {
        if (this.address == null) {
            this.address = new java.util.ArrayList<com.app.shared.organization.locationmanagement.Address>();
        }
        if (address != null) {
            this.address.addAll(address);
        }
        return this;
    }

    @JsonIgnore
    public Integer sizeOfAddress() {
        int count = 0;
        if (this.address != null) {
            count = this.address.size();
        }
        return count;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @JsonIgnore
    public List<Address> getDeletedAddressList() {
        List<Address> addressToRemove = new java.util.ArrayList<Address>();
        for (Address address : this.address) {
            if (address.isHardDelete()) {
                addressToRemove.add(address);
            }
        }
        address.removeAll(addressToRemove);
        return addressToRemove;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
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
        setValidatoraddress(validateFactory);
    }

    private void setValidatoraddress(EntityValidatorHelper<Object> validateFactory) {
        if (address != null) {
            for (int i = 0; i < address.size(); i++) {
                address.get(i).setEntityValidator(validateFactory);
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
        if (this.address == null) {
            this.address = new java.util.ArrayList<Address>();
        }
        for (Address address : this.address) {
            address.setEntityAudit(customerId, userId, recordType);
            address.setSystemInformation(recordType);
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
        if (this.address == null) {
            this.address = new java.util.ArrayList<Address>();
        }
        for (Address address : this.address) {
            address.setEntityAudit(customerId, userId);
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

    public String getTitleTitles() {
        return titleTitles;
    }

    public void setTitleTitles(String titleTitles) {
        this.titleTitles = titleTitles;
    }

    public String getLanguageLanguage() {
        return languageLanguage;
    }

    public void setLanguageLanguage(String languageLanguage) {
        this.languageLanguage = languageLanguage;
    }

    public String getGenderGender() {
        return genderGender;
    }

    public void setGenderGender(String genderGender) {
        this.genderGender = genderGender;
    }

    public Integer getTimezoneUtcdifference() {
        return timezoneUtcdifference;
    }

    public void setTimezoneUtcdifference(Integer timezoneUtcdifference) {
        this.timezoneUtcdifference = timezoneUtcdifference;
    }

    public String getTimezoneTimeZoneLabel() {
        return timezoneTimeZoneLabel;
    }

    public void setTimezoneTimeZoneLabel(String timezoneTimeZoneLabel) {
        this.timezoneTimeZoneLabel = timezoneTimeZoneLabel;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param CoreContacts
     * @param CoreContacts
     */
    @Override
    public int compare(CoreContacts object1, CoreContacts object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((firstName == null ? " " : firstName) + ",").append((middleName == null ? " " : middleName) + ",").append((lastName == null ? " " : lastName) + ",").append((emailId == null ? " " : emailId) + ",").append((phoneNumber == null ? " " : phoneNumber));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (contactId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = contactId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.organization.contactmanagement.CoreContacts other = (com.app.shared.organization.contactmanagement.CoreContacts) obj;
            if (contactId == null) {
                isEquals = false;
            } else if (!contactId.equals(other.contactId)) {
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
