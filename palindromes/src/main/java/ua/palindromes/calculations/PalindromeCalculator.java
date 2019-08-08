package ua.palindromes.calculations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PalindromeCalculator {
	public static List<String> findPalindrome(String number, BigInteger palindromesValue) {
		List<String> numbers = new ArrayList<>();
		for (int i = 1; i <= number.length(); i++) {
			for (int j = 0; j <= number.length(); j++) {
				if (i + j <= number.length()) {
					StringBuilder substring = new StringBuilder(number);
					String sub = substring.substring(j, j + i);
					substring = new StringBuilder(sub);
					String revers = substring.reverse().toString();
					if (sub.equals(revers) && sub.length() > 1
							&& palindromesValue.compareTo(new BigInteger("0")) == 1) {
						palindromesValue = palindromesValue.subtract(new BigInteger("-1"));
						System.out.println(sub);
						numbers.add(sub);
					}
				}
			}
		}
		return numbers;
	}
}
