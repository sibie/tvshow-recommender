package com.cbamz.tvshowrecommender.service.auth;

import com.cbamz.tvshowrecommender.domain.auth.User;
import com.cbamz.tvshowrecommender.domain.registration.Token;
import com.cbamz.tvshowrecommender.domain.tvshow.TvShow;
import com.cbamz.tvshowrecommender.repository.UserRepository;
import com.cbamz.tvshowrecommender.service.registration.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    // Message to be returned in case of an invalid user, i.e. email.
    private final static String USER_NOT_FOUND_MSG = "User with %s not found.";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DisabledException {
        return userRepository.findByEmail(email) // Constructs the query used to retrieve current user details.
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {
        // Checking if the user exists before proceeding.
        boolean userExists = userRepository.findByEmail(user.getEmail())
                .isPresent();

        // Exception handling for duplicate user.
        if(userExists) {
            // if(user.getEnabled() == true) {} --> if user created an account but token expired, resend the email..
            throw new IllegalStateException(user.getEmail() + " is already a registered user.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user); // If it is a new user, setting encrypted password and saving details to DB.

        String uuid = UUID.randomUUID().toString();
        Token token = new Token(uuid, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), user);
        tokenService.saveToken(token); // Generating a new token for the user and saving to DB.

        return uuid;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    } // Only enabled after they verify.

    public User setWatchHistory(String email, TvShow newShow) {
        // Using this method to set update a user's recommendation history with new show provided by recommend API.
        User user = userRepository.findByEmail(email).get(); // Getting current user's DB record.
        Set<TvShow> userHistory = user.getWatchHistory();
        userHistory.add(newShow);
        user.setWatchHistory(userHistory);
        return userRepository.save(user); // saving back to DB with updated set.
    }

    public User resetWatchHistory(String email) {
        // This is for resetting a user's history to an empty set.
        User user = userRepository.findByEmail(email).get();
        Set<TvShow> userHistory = user.getWatchHistory();
        userHistory.clear();
        user.setWatchHistory(userHistory);
        return userRepository.save(user);
    }

    public Set<TvShow> getWatchHistory(String email) {
        // Retrieves the user's current user history so we can provide a new show using recommend endpoint.
        User user = userRepository.findByEmail(email).get();
        Set<TvShow> userHistory = user.getWatchHistory();
        return userHistory;
    }

    public String getCachedShow(String email) {
        User user = userRepository.findByEmail(email).get();
        return user.getCachedShow();
    }

    public User setCachedShow(String email, String title) {
        User user = userRepository.findByEmail(email).get();
        user.setCachedShow(title);
        return userRepository.save(user);
    }
}
