package com.example.onlineshop.Service;

import com.example.onlineshop.Forms.UserRegistrationForm;
import com.example.onlineshop.Models.Entity.Role;
import com.example.onlineshop.Models.Entity.User;
import com.example.onlineshop.Models.Entity.UserRole;
import com.example.onlineshop.Repository.RoleRepository;
import com.example.onlineshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;


    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService){
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User findUserByUsername(String login){
        return userRepository.findByUsername(login);
    }
    public User findUserById(Integer user_id){
        return userRepository.findUserById(user_id);
    }


    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findUserByPhone(String phone){
        return userRepository.findByPhone(phone);
    }


    public boolean CheckPassword(String rawPassword, String login) {

        User user = userRepository.findByUsername(login);
        if (user != null) {

            String hashedPassword = user.getPassword();


            return BCrypt.checkpw(rawPassword, hashedPassword);
        }

        return false;

    }

    public void assignUserRole(User user){
        Role role = roleService.findByRoleName("user");
        if (role != null) {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            if (user.getRoles() == null) {
                user.setUserRoles(new HashSet<>());
            }
            user.getRoles().add(userRole);
        } else {
            // Obsługa, gdy rola nie zostanie znaleziona
            System.out.println("Rola 'user' nie została znaleziona.");
        }
    }










}
