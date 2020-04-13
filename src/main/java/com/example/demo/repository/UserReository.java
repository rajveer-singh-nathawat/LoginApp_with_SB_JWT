package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.SignupInfo;
import com.example.demo.dto.TokenInfo;
import com.example.demo.model.UserEntity;
@Repository
public interface UserReository extends JpaRepository<UserEntity, Long>{
	@Query(value = "SELECT new com.example.demo.dto.SignupInfo"
			+ "(u.userName,u.password,u.active,ure.roles)"
			+ " FROM UserEntity u "
			+ " JOIN UserRolesUserMappingEntity urm on urm.userEntity.userId=u.userId"
			+ " JOIN UserRolesEntity ure on ure.userRolesId = urm.userRolesEntity.userRolesId "
			+ " WHERE u.userName =:userName")
	public Optional<SignupInfo> findUserSignupInfo(@Param("userName") String userName);
	
	@Query(value = "SELECT new com.example.demo.dto.TokenInfo"
			+ "(u.userId,u.userName,upe.fullName,ure.roles)"
			+ " FROM UserEntity u "
			+ " JOIN UserRolesUserMappingEntity urm on urm.userEntity.userId=u.userId "
			+ " JOIN UserRolesEntity ure on ure.userRolesId = urm.userRolesEntity.userRolesId "
			+ " Join UserProfileEntity upe on upe.userEntity.userId =u.userId "
			+ " WHERE u.userName =:userName")
	public TokenInfo findTokenInfo(@Param("userName") String userName);
			
			Optional<UserEntity> findByUserName(String userName);

}
