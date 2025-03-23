package com.songify.domain.crud;

public class AlbumNotFoundException extends RuntimeException {

    AlbumNotFoundException(final String message) {
        super(message);
    }
}
