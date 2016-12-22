package com.app.bean;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.lang.Override;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SMSBean implements EntityValidatorInterface {

	@Transient
	@JsonIgnore
	private EntityValidatorHelper<Object> dtoValidator;

	private String configId;

	private ArrayList<String> mobilesNos;

	@Transient
	@JsonIgnore
	private boolean isDtoValidated = false;

	@JsonIgnore
	@Override
	public boolean isEntityValidated() {
		return isDtoValidated;
	}

	/**
	 * returns configuration api id
	 * 
	 * @return
	 */
	public String getConfigId() {
		return configId;
	}

	/**
	 * @param configId
	 */
	public void setConfigId(final String configId) {
		this.configId = configId;
	}

	/**
	 * Returns list of mobile numbers
	 * 
	 * @return
	 */
	public ArrayList<String> getMobilesNos() {
		return mobilesNos;
	}

	/**
	 * @param mobilesNos
	 */
	public void setMobilesNos(final ArrayList<String> mobilesNos) {
		this.mobilesNos = mobilesNos;
	}

	/**
	 * @param mobilesNos
	 * @return add mobile no to list and returns list of mobile numbers
	 */
	public SMSBean addMobilesNos(final String mobilesNos) {
		if (this.mobilesNos == null) {
			this.mobilesNos = new java.util.ArrayList<java.lang.String>();
		}
		if (mobilesNos != null) {
			this.mobilesNos.add(mobilesNos);
		}
		return this;
	}

	/**
	 * @param mobilesNos
	 * @return add mobile nos to list and returns list of mobile numbers
	 */
	public SMSBean addAllMobilesNos(final List<String> mobilesNos) {
		if (this.mobilesNos == null) {
			this.mobilesNos = new java.util.ArrayList<java.lang.String>();
		}
		if (mobilesNos != null) {
			this.mobilesNos.addAll(mobilesNos);
		}
		return this;
	}

	/**
	 * @param mobilesNos
	 * @return remove mobile number from list and return list of mobile numbers
	 */
	public SMSBean removeMobilesNos(final String mobilesNos) {
		if (this.mobilesNos != null) {
			this.mobilesNos.remove(mobilesNos);
		}
		return this;
	}

	/**
	 * @return size of Mobile numbers list
	 */
	public Integer sizeOfMobilesNos() {
		if (this.mobilesNos != null) {
			return this.mobilesNos.size();
		}
		return 0;
	}

	/**
	 * @param _validateFactory
	 */
	@Override
	public void setEntityValidator(EntityValidatorHelper<Object> validateFactory) {
		this.dtoValidator = validateFactory;
	}

	/**
	 * @return true/false after validation
	 */
	@JsonIgnore
	@Override
	public boolean isValid() throws SecurityException {
		boolean isValid = false;
		if (this.dtoValidator != null) {
			isValid = this.dtoValidator.validateEntity(this);
			this.isDtoValidated = true;
		} else {
			throw new SecurityException();
		}
		return isValid;
	}
}
