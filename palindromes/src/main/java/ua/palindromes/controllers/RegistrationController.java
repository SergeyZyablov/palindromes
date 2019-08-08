package ua.palindromes.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.palindromes.models.Role;
import ua.palindromes.models.User;
import ua.palindromes.repositories.RoleRepository;
import ua.palindromes.repositories.UserRepository;
import ua.palindromes.validations.UserValidator;

@Controller
public class RegistrationController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserValidator userValidator;

	@GetMapping("/registration")
	public String getRegistrationPage(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model,
			Errors errors) {
		userValidator.validate(user, errors);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		Role userRole = roleRepository.findOneByName("user");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		userRepository.save(user);
		return "redirect:/login";
	}

}
