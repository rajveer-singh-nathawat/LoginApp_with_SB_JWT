package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "user_profile")
public class UserProfileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userProfileId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "user_id", name = "user_id")
	private UserEntity userEntity;

	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name ="mobile_no")
	private String mobileNo;
}
