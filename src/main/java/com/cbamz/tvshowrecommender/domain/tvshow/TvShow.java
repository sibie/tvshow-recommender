package com.cbamz.tvshowrecommender.domain.tvshow;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TVSHOWS")
public class TvShow {
    // Entity to represent a single TV show.
    @Id
    private Long id;
    private String title;
    private String director;
    private String synopsis;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String releaseYear;
    private Float imdbRating;
    private String imdbLink;
}
