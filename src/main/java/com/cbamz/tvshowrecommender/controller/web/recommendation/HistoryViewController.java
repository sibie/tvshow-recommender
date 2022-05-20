package com.cbamz.tvshowrecommender.controller.web;

import com.cbamz.tvshowrecommender.domain.tvshow.TvShow;
import com.cbamz.tvshowrecommender.service.auth.UserService;
import com.cbamz.tvshowrecommender.service.recommendation.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
@RequestMapping("/history/")
@AllArgsConstructor
public class HistoryViewController {
    private final RecommendationService recommendationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWatchHistory(Model model) {
        Set<TvShow> set = recommendationService.getWatchHistory();
        model.addAttribute("set", set);
        return "history";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String resetWatchHistory(Model model) {
        recommendationService.resetWatchHistory();
        return "redirect:/history/";
    }
}
