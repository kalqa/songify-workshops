package com.songify.domain.crud;

import com.songify.domain.crud.dto.GenreDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class GenreRetriever {

    private final GenreRepository genreRepository;

    Genre findGenreById(Long genreId) {
        return genreRepository
                .findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("Genre with id " + genreId + " not found"));
    }

    Set<GenreDto> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(genre -> new GenreDto(genre.getId(), genre.getName()))
                .collect(Collectors.toSet());
    }
}
