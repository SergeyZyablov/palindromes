package ua.palindromes.validations;

import java.math.BigInteger;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class HistoryValidator {

	public void fieldsValodation(String number, String palindromes, Errors errors) {
		if (number.length() < 2) {
			errors.rejectValue("number", "", "The palindrome is incorrectly specified.");
		}
		BigInteger numberValue = new BigInteger(number);
		BigInteger palindromesValue = new BigInteger(palindromes);
		if (numberValue.compareTo(palindromesValue) == -1) {
			errors.rejectValue("palindromes", "", "The expected number of palindromes exceeds the number.");
		}
	}

}
