package com.example.demo.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dto.SignupInfo;

@SuppressWarnings("serial")
public class UserDetail implements UserDetails {
	private String username;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;

	public UserDetail(SignupInfo user) {
		this.username = user.getUserName();
		this.password = user.getPassword();
		this.active = user.getActive();
	this.authorities = Arrays.stream(user.getRoles().split(","))
		.map(SimpleGrantedAuthority :: new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
		return active;
	}

}
