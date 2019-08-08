package ua.palindromes.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.palindromes.models.User;
import ua.palindromes.repositories.UserRepository;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if (userRepository.existsByLogin(user.getLogin())) {
			errors.rejectValue("login", "", "Пользователь с таким логином уже существует");
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			errors.rejectValue("email", "", "Пользователь с такой почтой уже существует");
		}
	}

}
