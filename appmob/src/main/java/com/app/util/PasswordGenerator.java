package com.app.util;
import com.app.bean.PasswordLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PasswordGenerator {

	final private Random random = new Random();
	private int minPwdLength;
	private int maxPwdLenght;

	private static final Character[] SMALL_LETTERS = { 'a', 'b', 'c', 'd', 'e',
		'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',

		'o', 'p', 'q', 'r', 's', 'u', 'v', 'w', 'x', 'y', 'z' };

	private static final Character[] CAPITAL_LETTERS = {

		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
		'P', 'Q', 'R', 'S', 'T', 'U', 'V',

		'W', 'X', 'Y', 'Z' };

	private static final Character[] NUMBERS = { '0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9' };

	private Character[] SPECIAL_LETTERS = { '!', '-', '#', '$', '%', '(', ')',
		'*', '+', '-', '.', ':', ';', '<', '=', '>', '?', '@', '[', ']',
		'^', '_', '{', '|', '}', '~', };


	public int getMaxPwdLenght() {
		return maxPwdLenght;
	}

	public void setMaxPwdLenght(final int maxPwdLenght) {
		this.maxPwdLenght = maxPwdLenght;
	}

	public void setSPECIAL_LETTERS(final Character[] sPECIAL_LETTERS) {
		SPECIAL_LETTERS = sPECIAL_LETTERS;
	}

	public int getMinPwdLength() {
		return minPwdLength;
	}

	public void setMinPwdLength(int minPwdLength) {
		this.minPwdLength = minPwdLength;
	}

	public PasswordGenerator() {
		super();
	}

	public PasswordGenerator(int minPwdLength) {
		this.minPwdLength = minPwdLength;
	}

	public PasswordGenerator(int minPwdLength, int maxPwdLen) {
		this.minPwdLength = minPwdLength;
		this.maxPwdLenght = maxPwdLen;
	}

	private Set<Character> generateChars(Character[] charArray, int size) {

		Set<Character> chars = new HashSet<Character>();

		int arrSize = charArray.length;

		for (int i = 0; i < size;) {

			char ch = charArray[random.nextInt(arrSize)];

			if (!chars.contains(ch)) {

				i++;

				chars.add(ch);

			}
		}
		return chars;
	}

	public String generate(PasswordLogic[] passwordLogics) {
		// Generate random characters
		int totalNumChars = 0;
		List<Character> chars = new ArrayList<Character>();
		Character[] pwdChars = {};
		List<Character> allowed_all_chars = new ArrayList<Character>();

		for (PasswordLogic passwordLogic : passwordLogics) {
			if (passwordLogic.numChars > 0) {
				allowed_all_chars.add(passwordLogic.chars);
			}

			if (passwordLogic.chars == 'S') // Small Letters
				pwdChars = SMALL_LETTERS;
			else if (passwordLogic.chars == 'C')// Capital Letters
				pwdChars = CAPITAL_LETTERS;
			else if (passwordLogic.chars == 'N')// Numbers
				pwdChars = NUMBERS;
			else if (passwordLogic.chars == 'P')// Special Letters
				pwdChars = SPECIAL_LETTERS;
			totalNumChars = totalNumChars + passwordLogic.numChars;
			chars.addAll(generateChars(pwdChars, passwordLogic.numChars));
		}
		minPwdLength = minPwdLength - totalNumChars;
		if (minPwdLength != 0) {
			while (minPwdLength > 0) {
				char ch = allowed_all_chars.get(random.nextInt(allowed_all_chars.size()));

				if (ch == 'S') // Small Letters
					pwdChars = SMALL_LETTERS;
				else if (ch == 'C')// Capital Letters
					pwdChars = CAPITAL_LETTERS;
				else if (ch == 'N')// Numbers
					pwdChars = NUMBERS;
				else if (ch == 'P')// Special Letters
					pwdChars = SPECIAL_LETTERS;

				chars.addAll(generateChars(pwdChars, 1));
				minPwdLength--;
			}
		}

		// Generate random sequence
		StringBuffer sb = new StringBuffer();
		int size = chars.size();
		Set<Integer> sequence = new HashSet<Integer>();

		for (int i = 0; i < size;) {
			int pos = random.nextInt(size);
			if (!sequence.contains(pos)) {
				i++;
				sb.append(chars.get(pos));
				sequence.add(pos);
			}
		}
		return sb.toString();
	}

	public boolean validatePwdData(PasswordLogic[] passwordLogics) {
		int noOfSetCharacter = 0;
		String errorMSG = "";
		if (minPwdLength == 0)
			return false;
		if (maxPwdLenght < minPwdLength)
			return false;
		for (PasswordLogic passwordLogic : passwordLogics) {
			noOfSetCharacter = noOfSetCharacter + passwordLogic.numChars;
		}
		if (noOfSetCharacter == 0)
			return false;
		errorMSG = "";

		return true;
	}

	public String validatePassword(PasswordLogic[] passwordLogics, String password) {
		String msg = "";
		if (maxPwdLenght != 0 && password.length() < minPwdLength)
			msg = msg + "Password Must have at least " + minPwdLength + " characters.<br>";
		if (minPwdLength != 0 && password.length() > maxPwdLenght)
			msg = msg + "Password Must have at most " + maxPwdLenght + " characters.<br>";
		List<Character> smallLe = new ArrayList<Character>();
		List<Character> capLe = new ArrayList<Character>();
		List<Character> number = new ArrayList<Character>();
		List<Character> speLet = new ArrayList<Character>();
		char[] pwd = password.toCharArray();
		for (int cnt = 0; cnt < pwd.length; cnt++) {
			if (Arrays.asList(SMALL_LETTERS).contains(pwd[cnt]))
				smallLe.add(pwd[cnt]);
			if (Arrays.asList(CAPITAL_LETTERS).contains(pwd[cnt]))
				capLe.add(pwd[cnt]);
			if (Arrays.asList(NUMBERS).contains(pwd[cnt]))
				number.add(pwd[cnt]);
			if (Arrays.asList(SPECIAL_LETTERS).contains(pwd[cnt]))
				speLet.add(pwd[cnt]);
		}

		for (PasswordLogic passwordLogic : passwordLogics) {
			if (passwordLogic.numChars != 0) {

				if (passwordLogic.chars == 'S') // Small Letters
				{
					if (smallLe.size() < passwordLogic.numChars) {
						msg = msg + "Password Must have at least " + passwordLogic.numChars + " small letters.<br>";
					}
				} else if (passwordLogic.chars == 'C')// Capital Letters
				{
					if (capLe.size() < passwordLogic.numChars) {
						msg = msg + "Password Must have at least " + passwordLogic.numChars + " capital letters.<br>";
					}
				} else if (passwordLogic.chars == 'N')// Numbers
				{

					if (number.size() < passwordLogic.numChars) {
						msg = msg + "Password Must have at least " + passwordLogic.numChars + " numbers(0-9).<br>";
					}
				} else if (passwordLogic.chars == 'P')// Special Letters
				{
					if (speLet.size() < passwordLogic.numChars) {

						String s = null;
						for (int cnt = 0; cnt < SPECIAL_LETTERS.length; cnt++)
							s = s + SPECIAL_LETTERS[cnt];
						msg = msg + "Password Must have at least " + passwordLogic.numChars + " Special Letter(" + s + ").<br>";
					}

				}
			}
		}

		if (msg.length() > 0)
			return msg;

		return null;
	}

}
