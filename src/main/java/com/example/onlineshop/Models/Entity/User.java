package com.example.onlineshop.Models.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @Column(nullable = false,unique = true)
    @NotBlank(message = "Login is required")
    @Size(min = 3, max = 100, message = "Login must must contain 3 and 100 characters")
    private String login;
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\\\S+$).{8,}$", message = "Password must contain at least one digit, one uppercase letter, one lowercase letter, one special character, and must be at least 8 characters long.")
    private String password;
    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    @Size(min = 2, max=50, message = "First name must contain 2 and 50 characters")
    private String first_name;
    @Column(nullable = false)
    @NotBlank(message = "Last Name is required")
    @Size(min = 2, max=50, message = "Last name must contain 2 and 50 characters")
    private String last_name;
    @Size(min = 5, max = 255)
    private String address;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Phone numer is required")
    @Pattern(regexp = "^\\d{9}$")
    private String phone;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@Fetch(FetchMode.JOIN)
    private Set<UserRole> userRoles;

    public Set<UserRole> getRoles() {

        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getLogin() {
        return login;

    }

    public String getPassword() {
        return password;
    }

   public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());

    }



    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }



    public boolean isAccountNonExpired(){
        return true;
    }
    public boolean isCredentailsNonExpired(){
        return true;
    }
    public boolean isEnabled(){
        return true;
    }

}
