package com.cbamz.tvshowrecommender.controller.web;

import com.cbamz.tvshowrecommender.domain.registration.RegistrationRequest;
import com.cbamz.tvshowrecommender.service.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationViewController {

    private final RegistrationService registrationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register-user";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String registerUser(Model model, @ModelAttribute RegistrationRequest request) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        registrationService.register(request);
        return "redirect:/login";
    }
}
