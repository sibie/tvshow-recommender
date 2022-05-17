package com.cbamz.tvshowrecommender.domain.registration;

import lombok.*;

@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    // Represents the request body for any registration request.
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
