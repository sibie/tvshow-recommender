package com.cbamz.tvshowrecommender.service.registration;

import com.cbamz.tvshowrecommender.domain.registration.Token;
import com.cbamz.tvshowrecommender.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveToken(Token token) {
        // Used for saving newly generated tokens to our DB.
        tokenRepository.save(token);
    }

    // Need to mark this as Optional to account for the possibility of null objects being returned by DB query.
    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        // Service method to update the confirmation status of a token.
        return tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
