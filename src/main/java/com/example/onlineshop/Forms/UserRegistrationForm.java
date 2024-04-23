package com.example.onlineshop.Forms;

import com.example.onlineshop.Models.Entity.User;
import com.example.onlineshop.Validators.UserRegistrationValidator;
import jakarta.persistence.Column;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegistrationForm {
    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    @Size(min = 2, max=50, message = "First name must contain 2 and 50 characters")

    private String first_name;
    @Column(nullable = false)
    @NotBlank(message = "Last Name is required")
    @Size(min = 2, max=50, message = "Last name must contain 2 and 50 characters")
    private String last_name;
    @Column(nullable = false,unique = true)
    @NotBlank(message = "Login is required")
    @Size(min = 3, max = 100, message = "Login must must contain 3 and 100 characters")
    private String login;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\\\S+$).{8,}$", message = "Password must contain at least one digit, one uppercase letter, one lowercase letter, one special character, and must be at least 8 characters long.")
    @NotBlank(message = "Password is required")
    private String password;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Phone numer is required")
    @Pattern(regexp = "^\\d{9}$")
    private String phone;
    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;



    private UserRegistrationValidator validatorMessage;

    private User user = new User();

    public String getFirst_name() {
        return user.getFirst_name();
    }

    public void setFirst_name(String first_name) {
        user.setFirst_name(this.first_name = first_name);
    }

    public String getLast_name() {
        return user.getLast_name();
    }

    public void setLast_name(String last_name) {
        user.setLast_name(this.last_name = last_name);
    }

    public String getLogin() {
        return user.getLogin();
    }

    public void setLogin(String login) {
        user.setLogin(this.login = login);
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void setPassword(String password) {
        user.setPassword(this.password = password);
    }

    public String getPhone() {
        return user.getPhone();
    }

    public void setPhone(String phone) {
        user.setPhone(this.phone = phone);
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void setEmail(String email) {
        user.setEmail(this.email = email);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRegistrationValidator getValidatorMessage() {
        return validatorMessage;
    }

    public void setValidatorMessage(UserRegistrationValidator validatorMessage) {
        this.validatorMessage = validatorMessage;
    }



}
