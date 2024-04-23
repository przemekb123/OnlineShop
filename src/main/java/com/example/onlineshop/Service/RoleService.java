package com.example.onlineshop.Service;

import com.example.onlineshop.Models.Entity.Role;
import com.example.onlineshop.Models.Entity.User;
import com.example.onlineshop.Models.Entity.UserRole;
import com.example.onlineshop.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public boolean checkAdminPermission(String login){
        Role role = findByRoleName("admin");
        List<Role> userRoles = roleRepository.findRolesByUserLogin(login);


        boolean isAdmin = userRoles.stream()
                .anyMatch(n -> n.getRoleName().equals(role.getRoleName()));

        System.out.println(isAdmin);


        return isAdmin;


    }



}
