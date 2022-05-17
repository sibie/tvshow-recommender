package com.cbamz.tvshowrecommender.service.recommendation;

import com.cbamz.tvshowrecommender.domain.registration.Token;
import com.cbamz.tvshowrecommender.domain.tvshow.Genre;
import com.cbamz.tvshowrecommender.domain.tvshow.TvShow;
import com.cbamz.tvshowrecommender.repository.TvShowRepository;
import com.cbamz.tvshowrecommender.repository.TvShowSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TvShowService {

    private final TvShowRepository tvShowRepository;

    public List<TvShow> searchBasedOnGenre(String g) {
        // Service method to query based on genre provided as input param.
        TvShow filter = new TvShow();
        Genre genre = Genre.valueOf(g); // Genres are saved as enum, so we need this conversion.
        filter.setGenre(genre);
        Specification<TvShow> spec = new TvShowSpecification(filter); // Filtering based on user's choice of genre.
        return tvShowRepository.findAll(spec); // JPA method to return result based on spec object.
    }

    public List<TvShow> test() {
        return tvShowRepository.findAll();
    }

}
