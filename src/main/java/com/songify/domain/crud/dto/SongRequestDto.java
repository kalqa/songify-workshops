package com.songify.domain.crud.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record SongRequestDto(
        String name,
        Instant releaseDate,
        Long duration,
        SongLanguageDto language
) {
}
