package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SignupInfo;
import com.example.demo.model.UserDetail;
import com.example.demo.repository.UserReository;
@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	UserReository userRepsitory;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<SignupInfo> signupInfo = userRepsitory.findUserSignupInfo(userName);
	return signupInfo.map(UserDetail::new).get();
	}

}
