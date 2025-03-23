package com.songify.infrastructure.crud.song.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorSongResponseDto(String message, HttpStatus status) {
}
