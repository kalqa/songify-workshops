package com.songify.infrastructure.crud.song.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto(String message, HttpStatus status) {
}
