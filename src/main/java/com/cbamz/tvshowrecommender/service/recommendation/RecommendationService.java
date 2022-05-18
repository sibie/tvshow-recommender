package com.cbamz.tvshowrecommender.service.recommendation;

import com.cbamz.tvshowrecommender.domain.tvshow.TvShow;
import com.cbamz.tvshowrecommender.service.auth.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RecommendationService {

    private final UserService userService;
    private final TvShowService tvShowService;

    public TvShow recommend(String genre) {
        // Service method for recommending a show to user.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Current auth details.
        TvShow bestChoice = new TvShow();

        List<TvShow> queryResult = tvShowService.searchBasedOnGenre(genre); // Querying for shows based on genre input.
        Set<TvShow> userHistory = userService.getWatchHistory(authentication.getName()); // User history.

        queryResult.removeAll(userHistory); // Removing shows that user already watched from the result.

        if(queryResult.isEmpty()) {
            bestChoice.setTitle("NO OPTIONS AVAILABLE."); // In case there are no options available.
        } else {
            bestChoice = queryResult.get(new Random().nextInt(queryResult.size())); // Picking a random show for user.
            // userService.setRecommendationHistory(authentication.getName(), bestChoice);
        }
        return bestChoice; // We can customize this further. Eg - Pick the show with the highest IMDB score, etc.
    }

    public String resetWatchHistory() {
        // Service method for resetting a user's watch history.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.resetWatchHistory(authentication.getName());
        return "Watch history for user " + authentication.getName() + " has been reset.";
    }

    public String addToWatchHistory(TvShow tvShow) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.setWatchHistory(authentication.getName(), tvShow);
        return "Show has been added to user's watch history.";
    }

    public Set<TvShow> getWatchHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getWatchHistory(authentication.getName());
    }

    public TvShow getCache() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return tvShowService.loadTvShowByTitle(userService.getCachedShow(authentication.getName()));
    }

    public void setCache(String title) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.setCachedShow(authentication.getName(), title);
    }
}
