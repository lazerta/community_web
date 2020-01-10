package com.shawn.community.user.repository;

import com.shawn.community.user.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface AdminRepository extends JpaRepository<Admin, String>, QueryByExampleExecutor<Admin> {
    Admin findByUsername(String username);
}
