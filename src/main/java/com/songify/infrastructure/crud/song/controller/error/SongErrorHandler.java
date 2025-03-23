package com.songify.infrastructure.crud.song.controller.error;

import com.songify.domain.crud.SongNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class SongErrorHandler {

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<ErrorSongResponseDto> handleException(SongNotFoundException exception) {
        log.warn("SongNotFoundException while accessing song");
        ErrorSongResponseDto errorSongResponseDto = new ErrorSongResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorSongResponseDto);
    }
}
