package com.example.onlineshop.Repository;

import com.example.onlineshop.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u From User u Where u.login = :login")
    User findByUsername(String login);
    @Query("SELECT u From User u Where u.email = :email")
    User findByEmail(String email);
    @Query("SELECT u From User u Where u.phone = :phone")
    User findByPhone(String phone);
    @Query("SELECT u.password FROM User u WHERE u.login = :login")
    User findPasswordByUsername(String login);

    @Query("select u.user_id from User u where u.login = :login")
    User findUserIdByUsername(String login);

    @Query("select u from User u where u.user_id = :user_id")
    User findUserById(Integer user_id);

    Optional<User> findByLogin(String login);


    boolean existsByLogin(String login);

    boolean existsByEmail(String email);



}
