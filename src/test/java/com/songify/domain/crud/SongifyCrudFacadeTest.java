package com.songify.domain.crud;

import com.songify.domain.crud.dto.SongDto;
import com.songify.domain.crud.dto.SongLanguageDto;
import com.songify.domain.crud.dto.SongRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


class SongifyCrudFacadeTest {

    SongifyCrudFacade songifyCrudFacade = SongifyCrudFacadeConfiguration.createSongifyCrud(
            new InMemorySongRepository(),
            new InMemoryGenreRepository()
    );

    @Test
    @DisplayName("should retrieve song with genre")
    public void should_retrieve_song() {
        // given
        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        // when
        SongDto songDtoById = songifyCrudFacade.findSongDtoById(songDto.id());
        // then
        assertThat(songDtoById.genre().name()).isEqualTo("default");
        assertThat(songDtoById.genre().id()).isEqualTo(1);
        assertThat(songDtoById.id()).isEqualTo(0);
        assertThat(songDtoById.name()).isEqualTo("song1");
    }

    @Test
    @DisplayName("should add song")
    public void should_add_song() {
        // given
        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged())).isEmpty();
        // when
        songifyCrudFacade.addSong(song);
        // then
        List<SongDto> allSongs = songifyCrudFacade.findAllSongs(Pageable.unpaged());
        assertThat(allSongs)
                .extracting(SongDto::id)
                .containsExactly(0L);
    }

    @Test
    @DisplayName("should throw exception when song not found by id")
    public void should_throw_exception_when_song_not_found_by_id() {
        // given
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged())).isEmpty();
        // when
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findSongDtoById(55L));
        // then
        assertThat(throwable).isInstanceOf(SongNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Song with id 55 not found");
    }

}