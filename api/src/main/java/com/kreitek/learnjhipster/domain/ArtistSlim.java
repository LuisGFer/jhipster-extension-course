package com.kreitek.learnjhipster.domain;

import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Artist.
 */
@Entity
@Immutable
@Table(name = "v_artist_slim")
public class ArtistSlim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;


    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    public ArtistSlim() {
    }

    public ArtistSlim(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
