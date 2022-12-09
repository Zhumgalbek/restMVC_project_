package com.peaksoft.repository;

import com.peaksoft.entity.Role;
import com.peaksoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


}