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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class HomeViewController {
    private final RecommendationService recommendationService;

    @RequestMapping(path = {"/","/search"})
    public String home(Model model, String genre) {
        if(genre != null) {
            TvShow tvShow = recommendationService.recommend(genre);
            if(!tvShow.getTitle().contains("NO OPTIONS"))
                recommendationService.setCache(tvShow.getTitle());
            List<TvShow> list = new ArrayList<TvShow>();
            list.add(tvShow);
            model.addAttribute("list", list);
        }
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addToWatchHistory(Model model) {
        recommendationService.addToWatchHistory(recommendationService.getCache());
        return "redirect:/history/";
    }
}

    // Logout TBC
