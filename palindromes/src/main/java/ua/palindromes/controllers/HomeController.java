package ua.palindromes.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.palindromes.calculations.PalindromeCalculator;
import ua.palindromes.models.History;
import ua.palindromes.models.User;
import ua.palindromes.repositories.HistoryRepository;
import ua.palindromes.repositories.UserRepository;
import ua.palindromes.validations.HistoryValidator;

@Controller
public class HomeController {
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HistoryValidator historyValidator;

	@GetMapping("/")
	public String getHomePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByLogin(auth.getName()).orElse(new User());
		if (user.getId() != null) {
			model.addAttribute("historyList", user.getHistories());
		}
		model.addAttribute("history", new History());
		return "index";
	}

	@PostMapping("/calculation")
	public String calculation(String number, String palindromes, @Valid History history, BindingResult bindingResult,
			Model model, Errors errors) {
		historyValidator.fieldsValodation(number, palindromes, errors);
		if (bindingResult.hasErrors()) {
			return "index";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByLogin(auth.getName()).orElse(new User());
		BigInteger palindromesValue = new BigInteger(palindromes);
		if (user.getId() != null) {
			history.setNumber(number);
			history.setPalindromes(palindromes);
			history.setUser(user);
			historyRepository.save(history);
			model.addAttribute("historyList", user.getHistories());
			model.addAttribute("result", PalindromeCalculator.findPalindrome(number, palindromesValue));
			model.addAttribute("history", new History());
			return "index";
		}
		model.addAttribute("result", PalindromeCalculator.findPalindrome(number, palindromesValue));
		model.addAttribute("history", new History());
		return "index";

	}

}
