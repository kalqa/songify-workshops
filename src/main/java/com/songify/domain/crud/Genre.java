package com.songify.domain.crud;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class Genre extends BaseEntity {

    @Id
    @GeneratedValue(generator = "genre_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "genre_id_seq",
            sequenceName = "genre_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;

    Genre(final String name) {
        this.name = name;
    }

    Genre(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }
}
