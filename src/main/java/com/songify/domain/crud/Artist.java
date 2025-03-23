package com.songify.domain.crud;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class Artist extends BaseEntity {

    @Id
    @GeneratedValue(generator = "artist_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "artist_id_seq",
            sequenceName = "artist_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Album> albums = new HashSet<>();

    Artist(final String name) {
        this.name = name;
    }

    void removeAlbum(Album album) {
        albums.remove(album);
//        album.removeArtist(this);
    }

    void addAlbum(final Album album) {
        albums.add(album);
        album.addArtist(this);
    }
}
