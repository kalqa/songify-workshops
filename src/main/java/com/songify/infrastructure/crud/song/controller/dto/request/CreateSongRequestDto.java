package com.songify.infrastructure.crud.song.controller.dto.request;


public record CreateSongRequestDto(
        String songName,
        String artist
) {
}
