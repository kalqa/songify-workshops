package com.songify.domain.crud.dto;

import java.util.Set;

public record AlbumDto(
        Long id,
        String name,
        Set<Long> songsIds
) {
}
