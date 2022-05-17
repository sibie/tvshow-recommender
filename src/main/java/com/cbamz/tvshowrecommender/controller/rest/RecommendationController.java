package com.cbamz.tvshowrecommender.controller.rest;

import com.cbamz.tvshowrecommender.domain.tvshow.TvShow;
import com.cbamz.tvshowrecommender.service.recommendation.RecommendationService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/recommendation")
@AllArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    // This endpoint can be used to reset the current user's recommendation history.
    @RequestMapping(value = "/reset", method = {RequestMethod.GET, RequestMethod.POST})
    public String resetRecommendationHistory() {
        return recommendationService.resetWatchHistory();
    }

    // This endpoint can be used to recommend a unique show to the user based on selected genre.
    @RequestMapping(value = "/recommend", method = {RequestMethod.GET, RequestMethod.POST})
    public TvShow recommend(@RequestParam("genre") String genre) {
        return recommendationService.recommend(genre);
    }
}
