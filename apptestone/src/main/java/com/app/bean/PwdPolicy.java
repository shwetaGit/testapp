package com.app.bean;
public class PwdPolicy {
	private int pwdType;
	private Integer minLength;
	private Integer maxLength;
	private Integer minCapLetters;
	private Integer minSmlLetters;
	private Integer minSplLetters;
	private String allowedSplLetters;
	private Integer minNumLeters;
	private int encryptionType;
	private int pwdExpireDays;
	private int pwdReused;

	public PwdPolicy() {
		encryptionType = 0;// No encryption
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(final Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(final Integer maxLength) {
		this.maxLength = maxLength;
	}

	public Integer getMinCapLetters() {
		return minCapLetters;
	}

	public void setMinCapLetters(final Integer minCapLetters) {
		this.minCapLetters = minCapLetters;
	}

	public Integer getMinSmlLetters() {
		return minSmlLetters;
	}

	public void setMinSmlLetters(final Integer minSmlLetters) {
		this.minSmlLetters = minSmlLetters;
	}

	public Integer getMinSplLetters() {
		return minSplLetters;
	}

	public void setMinSplLetters(final Integer minSplLetters) {
		this.minSplLetters = minSplLetters;
	}

	public String getAllowedSplLetters() {
		return allowedSplLetters;
	}

	public void setAllowedSplLetters(final String allowedSplLetters) {
		this.allowedSplLetters = allowedSplLetters;
	}

	public Integer getMinNumLeters() {
		return minNumLeters;
	}

	public void setMinNumLeters(final Integer minNumLeters) {
		this.minNumLeters = minNumLeters;
	}

	public int getPwdType() {
		return pwdType;
	}

	public void setPwdType(final int pwdType) {
		this.pwdType = pwdType;
	}

	public int getEncryptionType() {
		return encryptionType;
	}

	public void setEncryptionType(final int encryptionType) {
		this.encryptionType = encryptionType;
	}

	public int getPwdExpireDays() {
		return pwdExpireDays;
	}

	public void setPwdExpireDays(final int pwdExpireDays) {
		this.pwdExpireDays = pwdExpireDays;
	}

	public int getPwdReused() {
		return pwdReused;
	}

	public void setPwdReused(final int pwdReused) {
		this.pwdReused = pwdReused;
	}

}
