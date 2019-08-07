package ua.palindromes.servises;

import java.util.Optional;

import ua.palindromes.models.User;


public interface UserService {
	void save(User user);

	Optional<User> findByLogin(String login);
}