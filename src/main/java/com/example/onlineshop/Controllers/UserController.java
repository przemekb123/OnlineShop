package com.example.onlineshop.Controllers;

import com.example.onlineshop.Forms.UserLoginForm;
import com.example.onlineshop.Forms.UserRegistrationForm;
import com.example.onlineshop.Models.Entity.Role;
import com.example.onlineshop.Models.Entity.User;
import com.example.onlineshop.Repository.RoleRepository;
import com.example.onlineshop.Repository.UserRepository;
import com.example.onlineshop.Service.RoleService;
import com.example.onlineshop.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private RoleService roleService;

    public UserController(UserRepository userRepository) {
    }


    @GetMapping("/users")
    public String ShowuserList(Model model, @RequestParam(defaultValue = "0") int page) {
        System.out.println("Wyswietlono rekordy");

        Pageable pageable = PageRequest.of(page, 10);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("Users", users);
        return "users";
    }





    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new UserRegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    @PreAuthorize("permitAll()")
    public String userRegistration(@Valid @ModelAttribute UserRegistrationForm userRegForm,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User userTest = userRegForm.getUser();
        userTest.setRegistrationDate(LocalDateTime.now());
        userService.assignUserRole(userTest);

        userRepository.save(userTest);

        redirectAttributes.addFlashAttribute("SuccessMessage", "User Registered successfully!");

        return "redirect:/login";


    }


    @GetMapping("/login")
    public String userLoginShow(Model model) {
        model.addAttribute("userForm", new UserLoginForm());

        if (httpSession.getAttribute("loggedInUser") != null) {
            model.addAttribute("loggedIn", true);

            return "redirect:/";
        }


        return "login";
    }


    @PostMapping("/login")
    public String userLogin(@Valid UserLoginForm userLoginForm , BindingResult bindingResult,Model model) {

        if(bindingResult.hasErrors()){
            return "login";
        }

        String username = userLoginForm.getLogin();
        String password = userLoginForm.getPassword();

        if (userService.CheckPassword(password, username)) {
            httpSession.setAttribute("loggedInUser", true);
            httpSession.setAttribute("username", username);

            if(roleService.checkAdminPermission(username)){
                httpSession.setAttribute("AdminPermission", true);
            }
            return "redirect:/";
        } else {
            return "redirect:/login?error";
        }

    }

    @GetMapping("/logout")
    public String userLogout() {

        // Usunięcie informacji o zalogowanym użytkowniku z sesji
        httpSession.removeAttribute("loggedInUser");
        httpSession.removeAttribute("AdminPermission");
        return "redirect:/login";
    }






}



