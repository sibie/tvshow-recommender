package com.cbamz.tvshowrecommender.domain.auth;

import com.cbamz.tvshowrecommender.domain.tvshow.TvShow;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    // Essential data to identify any user of the application.
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // Indicates the user role to handle permissions. USER or ADMIN are the only options, so using Enum here.
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    // Additional fields to be used for security purposes. Setting defaults to false.
    private Boolean locked = false;
    private Boolean enabled = false;

    // List to store user's recommendation history.
    @ElementCollection
    @CollectionTable(name = "user_watch_history", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "watch_history")
    private Set<TvShow> watchHistory;

    private String cachedShow;

    public User(String firstName,
                String lastName,
                String email,
                String password,
                UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.watchHistory = new HashSet<>();
        this.cachedShow = "";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Not implementing this feature, so returning true by default.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Not implementing this feature, so returning true by default.
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Set<TvShow> getWatchHistory() {
        return watchHistory;
    }

    public void setWatchHistory(Set<TvShow> watchHistory) {
        this.watchHistory = watchHistory;
    }

    public String getCachedShow() {
        return cachedShow;
    }

    public void setCachedShow(String title) {
        this.cachedShow = title;
    }
}