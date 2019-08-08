package ua.palindromes.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@NotEmpty(message = "The field is not filled")
	@Size(min = 6, max = 50, message = "Login must be more than 6 characters!")
	private String login;
	@NotEmpty(message = "The field is not filled")
	@Size(min = 6, max = 50, message = "Password must be more than 6 characters")
	private String password;
	@NotEmpty(message = "The field is not filled")
	@Email(message = "The login specified is incorrect.")
	private String email;
	@OneToMany(mappedBy="user")
	private List<History> histories;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_has_roles", joinColumns = {
			@JoinColumn(name = "users_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "roles_id", nullable = false, updatable = false) })
	private List<Role> roles;
}
