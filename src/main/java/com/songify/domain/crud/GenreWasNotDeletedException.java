package com.songify.domain.crud;

class GenreWasNotDeletedException extends RuntimeException {
    GenreWasNotDeletedException(final String message) {
        super(message);
    }
}
