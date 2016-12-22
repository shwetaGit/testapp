package com.app.shared;
import javax.persistence.Embeddable;
import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class SystemInfo {

    private static final long serialVersionUID = 1482406435112L;

    @Column(name = "activeStatus")
    @JsonProperty("activeStatus")
    private Integer activeStatus = 1;

    @Column(name = "txnAccessCode")
    @JsonProperty("txnAccessCode")
    private Integer txnAccessCode = 51000;

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Integer getTxnAccessCode() {
        return txnAccessCode;
    }

    public void setTxnAccessCode(Integer txnAccessCode) {
        this.txnAccessCode = txnAccessCode;
    }
}
