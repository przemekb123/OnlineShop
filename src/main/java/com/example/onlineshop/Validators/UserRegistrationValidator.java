package com.example.onlineshop.Validators;

public class UserRegistrationValidator {

    private String loginError;
    private String emialError;
    private String phoneError;


    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    public String getEmialError() {
        return emialError;
    }

    public void setEmialError(String emialError) {
        this.emialError = emialError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }
}
