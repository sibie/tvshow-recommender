package com.cbamz.tvshowrecommender.repository;

import com.cbamz.tvshowrecommender.domain.tvshow.TvShow;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TvShowSpecification implements Specification<TvShow> {
    // Implementation of Specification for TvShow objects.
    private TvShow filter;

    public TvShowSpecification(TvShow filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<TvShow> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate p = cb.disjunction();

        // Getting a query result based on genre. This can be extended to use more inputs.
        if (filter.getGenre() != null) {
            p.getExpressions()
                    .add(cb.equal(root.get("genre"), filter.getGenre()));
        } // We can add more filters here for other unique search criterias we want to implement.

        /* --> Code snippet to filter based on multiple criteria. Need to test if this works.
        if (filter.getGenre() != null && filter.getReleaseYear() != null) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("genre"), filter.getGenre()),
                            cb.equal(root.get("release_year"), filter.getReleaseYear())));
        }*/

        return p;
    }
}
