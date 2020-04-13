package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserRolesUserMappingEntity;

@Repository
public interface UserRolesUserMappingEntityRepository extends JpaRepository<UserRolesUserMappingEntity, Long>{

}
