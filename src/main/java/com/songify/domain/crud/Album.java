package com.songify.domain.crud;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class Album extends BaseEntity {

    @Id
    @GeneratedValue(generator = "album_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "album_id_seq",
            sequenceName = "album_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String title;

    private Instant releaseDate;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "album_id")
    private Set<Song> songs = new HashSet<>();

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artists = new HashSet<>();

    void addSongToAlbum(final Song song) {
        songs.add(song);
    }

    void addSongsToAlbum(final Set<Song> songs) {
        this.songs.addAll(songs);
    }

    void removeArtist(Artist artist) {
        artists.remove(artist);
        artist.removeAlbum(this);
    }

    void addArtist(final Artist artist) {
        artists.add(artist);
    }

    public Set<Long> getSongsIds() {
        return this.songs.stream()
                .map(Song::getId)
                .collect(Collectors.toSet());
    }
}
