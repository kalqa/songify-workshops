package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
class ArtistAdder {

    private final ArtistRepository artistRepository;
    private final AlbumAdder albumAdder;

    ArtistDto addArtist(final String name) {
        Artist save = saveArtist(name);
        return new ArtistDto(save.getId(), save.getName());
    }

    private Artist saveArtist(final String name) {
        Artist artist = new Artist(name);
        Artist save = artistRepository.save(artist);
        return save;
    }

    ArtistDto addArtistWithDefaultAlbumAndSong(final ArtistRequestDto dto) {
        String artistName = dto.name();
        Artist save = saveArtistWithDefaultAlbumAndSong(artistName);
        return new ArtistDto(save.getId(), save.getName());
    }

    private Artist saveArtistWithDefaultAlbumAndSong(final String name) {
        Artist artist = new Artist(name);

        Album album = new Album();
        album.setTitle("default-album:" + UUID.randomUUID());
        album.setReleaseDate(LocalDateTime.now().toInstant(ZoneOffset.UTC));

        Song song = new Song("default-song-name: " + UUID.randomUUID());

        album.addSongToAlbum(song);
        artist.addAlbum(album); // <----
        return artistRepository.save(artist);

//        better way is using AlbumAdder, SongAdder and specific methods, for me, not using cascade!
        // Album album = albumAdder.addAlbum(
        //                "default-album:" + UUID.randomUUID(),
        //                LocalDateTime.now().toInstant(ZoneOffset.UTC));
        //        Song song = songAdder.addSongAndGetEntity(new SongRequestDto(
        //                "default-song-name: " + UUID.randomUUID(),
        //                LocalDateTime.now().toInstant(ZoneOffset.UTC),
        //                0L,
        //                SongLanguageDto.OTHER
        //        ));
    }
}
