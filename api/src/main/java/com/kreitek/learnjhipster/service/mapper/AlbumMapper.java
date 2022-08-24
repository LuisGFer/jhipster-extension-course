package com.kreitek.learnjhipster.service.mapper;

import com.kreitek.learnjhipster.domain.Album;
import com.kreitek.learnjhipster.domain.Artist;
import com.kreitek.learnjhipster.domain.Style;
import com.kreitek.learnjhipster.service.dto.AlbumDTO;
import com.kreitek.learnjhipster.service.dto.ArtistDTO;
import com.kreitek.learnjhipster.service.dto.StyleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Album} and its DTO {@link AlbumDTO}.
 */
@Mapper(componentModel = "spring")
public interface AlbumMapper extends EntityMapper<AlbumDTO, Album> {
    @Mapping(target = "artist", source = "artist", qualifiedByName = "artistName")
    @Mapping(target = "style", source = "style", qualifiedByName = "styleName")
    AlbumDTO toDto(Album s);

    @Override
    @Mapping(target = "songs", ignore = true)
    @Mapping(target = "removeSongs", ignore = true)
    Album toEntity(AlbumDTO dto);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "songs", ignore = true)
    @Mapping(target = "removeSongs", ignore = true)
    void partialUpdate(@MappingTarget Album entity, AlbumDTO dto);

    @Named("artistName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ArtistDTO toDtoArtistName(Artist artist);

    @Named("styleName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    StyleDTO toDtoStyleName(Style style);
}
