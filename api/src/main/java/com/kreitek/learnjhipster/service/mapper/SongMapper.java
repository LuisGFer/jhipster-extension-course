package com.kreitek.learnjhipster.service.mapper;

import com.kreitek.learnjhipster.domain.Album;
import com.kreitek.learnjhipster.domain.Song;
import com.kreitek.learnjhipster.service.dto.AlbumDTO;
import com.kreitek.learnjhipster.service.dto.SongDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Song} and its DTO {@link SongDTO}.
 */
@Mapper(componentModel = "spring")
public interface SongMapper extends EntityMapper<SongDTO, Song> {
    @Mapping(target = "album", source = "album", qualifiedByName = "albumTitle")
    SongDTO toDto(Song s);

    @Named("albumTitle")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    AlbumDTO toDtoAlbumTitle(Album album);
}
