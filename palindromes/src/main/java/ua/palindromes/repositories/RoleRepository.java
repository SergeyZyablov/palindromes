package ua.palindromes.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.palindromes.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	List<Role> findAllById(Set<Long> singleton);

	Role findOneByName(String string);

}
