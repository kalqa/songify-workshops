package com.songify.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class GenreDeleter {

    private final GenreRepository genreRepository;

    void deleteById(final Long genreId) {
        int i = genreRepository.deleteById(genreId);
        if(i != 1){
            throw new GenreWasNotDeletedException("genre id: " + genreId + " not deleted");
        }
    }
}
