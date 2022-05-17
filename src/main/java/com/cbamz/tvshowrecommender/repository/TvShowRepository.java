package com.cbamz.tvshowrecommender.repository;

import com.cbamz.tvshowrecommender.domain.tvshow.TvShow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long>, JpaSpecificationExecutor<TvShow> {
    // Used for CRUD operations to TVSHOWS table. using SpecificationExecutor for custom search queries.
}
