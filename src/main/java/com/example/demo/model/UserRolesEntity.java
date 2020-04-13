package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_roles")
public class UserRolesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userRolesId;

	@Column(name = "roles")
	private String roles;


}
