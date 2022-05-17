package com.cbamz.tvshowrecommender.controller.rest;

import com.cbamz.tvshowrecommender.domain.registration.RegistrationRequest;
import com.cbamz.tvshowrecommender.service.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // This endpoint controls the initial registration process to accept a new user.
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    // This endpoint can be used to confirm a user's account using email confirmation of token.
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
