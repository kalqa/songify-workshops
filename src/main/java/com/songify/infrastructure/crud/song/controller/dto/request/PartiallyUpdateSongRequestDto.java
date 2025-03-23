package com.songify.infrastructure.crud.song.controller.dto.request;

public record PartiallyUpdateSongRequestDto(
        String songName,
        String artist
) {
}
