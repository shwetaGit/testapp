package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.server.password.interfaces.PassRecoveryInterface;
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

@Table(name = "PassRecovery")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "4", comments = "PassRecovery", complexity = Complexity.LOW)
@SqlResultSetMapping(name = "PassRecoveryResultSetMapping", classes = @javax.persistence.ConstructorResult(targetClass = com.app.shared.appbasicsetup.usermanagement.PassRecovery.class, columns = { @javax.persistence.ColumnResult(name = "passRecoveryId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "userdetailAuthAlgo", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "questionId", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "questionLevelid", type = java.lang.Integer.class), @javax.persistence.ColumnResult(name = "questionQuestion", type = java.lang.String.class), @javax.persistence.ColumnResult(name = "answer", type = java.lang.String.class) }))
@NamedQueries({ @javax.persistence.NamedQuery(name = "PassRecovery.findByQuestionId", query = "select e from PassRecovery e where e.systemInfo.activeStatus=1 and e.questionId=:questionId"), @javax.persistence.NamedQuery(name = "PassRecovery.findByUserId", query = "select e from PassRecovery e where e.systemInfo.activeStatus=1 and e.userDetail.userId=:userId"), @javax.persistence.NamedQuery(name = "PassRecovery.findById", query = "select e from PassRecovery e where e.systemInfo.activeStatus=1 and e.passRecoveryId =:passRecoveryId") })
public class PassRecovery implements Serializable, CommonEntityInterface, PassRecoveryInterface, Comparator<PassRecovery> {

    private static final long serialVersionUID = 1482410095289L;

    @Column(name = "answer")
    @JsonProperty("answer")
    @NotNull
    @Size(min = 0, max = 256)
    private String answer;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "passRecoveryId")
    @JsonProperty("passRecoveryId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String passRecoveryId;

    @Column(name = "questionId")
    @JsonProperty("questionId")
    private String questionId;

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

    @Transient
    private Integer questionLevelid;

    @Transient
    private String questionQuestion;

    public PassRecovery() {
    }

    public PassRecovery(String passRecoveryId, String userdetailAuthAlgo, String questionId, Integer questionLevelid, String questionQuestion, String answer) {
        this.passRecoveryId = passRecoveryId;
        this.userdetailAuthAlgo = userdetailAuthAlgo;
        this.questionId = questionId;
        this.questionLevelid = questionLevelid;
        this.questionQuestion = questionQuestion;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        if (answer != null) {
            this.answer = answer;
        }
    }

    public String getPrimaryKey() {
        return passRecoveryId;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String _getPrimarykey() {
        return passRecoveryId;
    }

    public String getPassRecoveryId() {
        return passRecoveryId;
    }

    public void setPassRecoveryId(String passRecoveryId) {
        this.passRecoveryId = passRecoveryId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
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

    public Integer getQuestionLevelid() {
        return questionLevelid;
    }

    public void setQuestionLevelid(Integer questionLevelid) {
        this.questionLevelid = questionLevelid;
    }

    public String getQuestionQuestion() {
        return questionQuestion;
    }

    public void setQuestionQuestion(String questionQuestion) {
        this.questionQuestion = questionQuestion;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((questionId.toString() == null ? " " : questionId.toString()) + ",").append((answer == null ? " " : answer));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        int hashcode = 0;
        if (passRecoveryId == null) {
            hashcode = super.hashCode();
        } else {
            hashcode = passRecoveryId.hashCode();
        }
        return hashcode;
    }

    public boolean equals(Object obj) {
        boolean isEquals = true;
        try {
            com.app.shared.appbasicsetup.usermanagement.PassRecovery other = (com.app.shared.appbasicsetup.usermanagement.PassRecovery) obj;
            if (passRecoveryId == null) {
                isEquals = false;
            } else if (!passRecoveryId.equals(other.passRecoveryId)) {
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
    public int compare(PassRecovery object1, PassRecovery object2) {
        switch(((fieldName))) {
            case "passRecoveryId":
                return (object1.getPassRecoveryId().compareTo(object2.getPassRecoveryId()) == 0) ? 0 : ((object1.getPassRecoveryId().compareTo(object2.getPassRecoveryId()) > 0) ? 1 : -1);
        }
        return 0;
    }
}
