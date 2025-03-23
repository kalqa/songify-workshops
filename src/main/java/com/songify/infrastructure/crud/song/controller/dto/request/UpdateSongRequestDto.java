package com.songify.infrastructure.crud.song.controller.dto.request;

public record UpdateSongRequestDto(
        String songName,
        String artist) {
}
