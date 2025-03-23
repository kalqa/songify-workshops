package com.songify.domain.crud;

import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Song {

    private Long id;

    private String name;

    private Instant releaseDate;

    private Long duration;

    private SongLanguage language;

    public Song(String name) {
        this.name = name;
    }

    Song(final String name, final Instant releaseDate, final Long duration, final SongLanguage language) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.language = language;
    }
}
