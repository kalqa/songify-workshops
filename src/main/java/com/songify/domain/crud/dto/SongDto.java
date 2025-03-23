package com.songify.domain.crud.dto;

import lombok.Builder;

@Builder
public record SongDto(
        Long id,
        String name,
        GenreDto genre
) {

}
