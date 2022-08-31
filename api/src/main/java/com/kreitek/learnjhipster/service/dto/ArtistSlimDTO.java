package com.kreitek.learnjhipster.service.dto;

public class ArtistSlimDTO {
    private Long id;
    private String name;

    public ArtistSlimDTO() {
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
