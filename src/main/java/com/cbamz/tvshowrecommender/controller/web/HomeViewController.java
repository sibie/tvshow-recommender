package com.cbamz.tvshowrecommender.controller.web;

import com.cbamz.tvshowrecommender.domain.tvshow.TvShow;
import com.cbamz.tvshowrecommender.service.recommendation.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeViewController {
    private final RecommendationService recommendationService;

    // Logout TBC

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST}, params="recommend")
    public String recommend(Model model, @RequestParam("genre") String genre) {
        TvShow tvShow = recommendationService.recommend(genre);
        model.addAttribute("tvShow", new TvShow());
        return "redirect:/home/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWatchHistory(Model model) {
        Set<TvShow> watchHistory = recommendationService.getWatchHistory();
        model.addAttribute("watchHistory", watchHistory);
        model.addAttribute("tvShow", new TvShow());
        return "home";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST}, params="add")
    public String addToWatchHistory(Model model, @ModelAttribute TvShow tvShow) {
        recommendationService.addToWatchHistory(tvShow);
        return "redirect:/home/";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST}, params="reset")
    public String resetWatchHistory(Model model) {
        recommendationService.resetWatchHistory();
        return "redirect:/home/";
    }
}
