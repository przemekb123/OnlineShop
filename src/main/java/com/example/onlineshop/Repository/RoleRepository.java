package com.example.onlineshop.Repository;

import com.example.onlineshop.Models.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
     Role findByRoleName(String roleName);
     @Query("SELECT ur.role FROM UserRole ur WHERE ur.user.login = :username")
     List<Role> findRolesByUserLogin(@Param("username") String username);
}
