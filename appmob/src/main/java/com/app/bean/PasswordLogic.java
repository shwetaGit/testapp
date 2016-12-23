package com.app.bean;
public class PasswordLogic {

	public char chars;

	public int numChars;

	/**
	 * initialize the passwordLogic with given configuration
	 * 
	 * @param : chars, numChars
	 * @returns : void
	 */
	public PasswordLogic(final char chars, final int numChars) {

		this.numChars = numChars;

		this.chars = chars;

	}

	public char getChars() {

		return chars;

	}

	public void setChars(final char chars) {

		this.chars = chars;

	}

	public int getNumChars() {

		return numChars;

	}

	public void setNumChars(final int numChars) {

		this.numChars = numChars;

	}

}
