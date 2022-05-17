package com.cbamz.tvshowrecommender.service.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return true; // This can be used for validating if the user email is legit or not. Yet to implement.
    }
}
