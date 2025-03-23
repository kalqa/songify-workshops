package com.songify.infrastructure.crud.song.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateSongRequestDto(
        @NotNull(message = "songName must not be null")
        @NotEmpty(message = "songName must not be empty")
        String songName,

        @NotNull(message = "artist must not be null")
        @NotEmpty(message = "artist must not be empty")
        String artist) {
}
