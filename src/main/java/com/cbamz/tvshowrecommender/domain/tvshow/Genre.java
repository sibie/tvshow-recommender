package com.cbamz.tvshowrecommender.domain.tvshow;

public enum Genre {
    ACTION("Action"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    SCIFI("Action");

    private final String displayValue;

    private Genre(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
