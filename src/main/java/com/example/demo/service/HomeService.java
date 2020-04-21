package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.UserRegisterDetailsRequest;
import com.example.demo.exception.IncorrectCredentials;
import com.example.demo.model.UserEntity;
import com.example.demo.model.UserProfileEntity;
import com.example.demo.model.UserRolesEntity;
import com.example.demo.model.UserRolesUserMappingEntity;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.repository.UserReository;
import com.example.demo.repository.UserRolesUserMappingEntityRepository;
import com.example.demo.util.JwtUtil;

@Service
public class HomeService {
	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private UserReository userReository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private UserRolesUserMappingEntityRepository userRoleRepo;

	@Autowired
	private JwtUtil jwtTokenUtil;

	public ResponseEntity<HttpStatus> registerUser(UserRegisterDetailsRequest userRegisterDetailsRequest) {
		
		saveUserDetails(userRegisterDetailsRequest);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);

	}

	private void saveUserDetails(UserRegisterDetailsRequest userRegisterDetailsRequest) {
		UserEntity user = new UserEntity();
		user.setUserName(userRegisterDetailsRequest.getUserName());
		user.setPassword(userRegisterDetailsRequest.getPassword());
		user.setActive(true);
		userReository.save(user);
		saveUserRolesUserMApping(user);
		saveRegisterUser(userRegisterDetailsRequest,user);
	}

	private void saveUserRolesUserMApping(UserEntity user) {
		UserRolesUserMappingEntity urmu = new UserRolesUserMappingEntity();
		UserRolesEntity userRole = new UserRolesEntity();
		userRole.setUserRolesId((long) 2);
		userRole.setRoles("ROLE_USER");
		urmu.setUserEntity(user);
		urmu.setUserRolesEntity(userRole);
		userRoleRepo.save(urmu);
		
	}

	private void saveRegisterUser(UserRegisterDetailsRequest userRegisterDetailsRequest, UserEntity user) {
		UserProfileEntity userRegisterDetails = new UserProfileEntity();
		userRegisterDetails.setEmail(userRegisterDetailsRequest.getEmail());
		userRegisterDetails.setFullName(userRegisterDetailsRequest.getFullName());
		userRegisterDetails.setMobileNo(userRegisterDetailsRequest.getMobileNo());
		userRegisterDetails.setUserEntity(user);
		userProfileRepository.save(userRegisterDetails);
	}

	public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws IncorrectCredentials  {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getuserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new IncorrectCredentials(e.getMessage());
		}
		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getuserName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
