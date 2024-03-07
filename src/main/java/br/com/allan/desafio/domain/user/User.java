package br.com.allan.desafio.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Entity(name="users")
@Table(name="users")
@Getter
@AllArgsConstructor
@Setter
@EqualsAndHashCode(of="id")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(unique=true)
	private String document;
	@Column(unique=true)
	private String email;
	private String login;
	private String password;
	private UserType userType;
	private BigDecimal balance;
	
	public User(){}

	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getDocument() {
		return document;
	}




	public void setDocument(String document) {
		this.document = document;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}


	public String getLogin() { return login; }

	public void setLogin(String login) { this.login = login; }




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public void setPassword(String password) {
		this.password = password;
	}




	public UserType getUserType() {
		return userType;
	}




	public void setUserType(UserType userType) {
		this.userType = userType;
	}




	public BigDecimal getBalance() {
		return balance;
	}




	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}




	public User(UserDto dto) {
		this.name = dto.name();
		this.document = dto.document();
		this.email = dto.email();
		this.password = dto.password();
		this.userType = dto.userType();
		this.balance = dto.balance();
	}

}
