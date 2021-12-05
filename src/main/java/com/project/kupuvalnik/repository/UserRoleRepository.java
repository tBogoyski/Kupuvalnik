package com.project.kupuvalnik.repository;

import com.project.kupuvalnik.models.entity.UserRoleEntity;
import com.project.kupuvalnik.models.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(UserRoleEnum role);
}
