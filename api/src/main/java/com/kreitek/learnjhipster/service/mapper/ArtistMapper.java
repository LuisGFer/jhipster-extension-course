package com.kreitek.learnjhipster.service.mapper;

import com.kreitek.learnjhipster.domain.Artist;
import com.kreitek.learnjhipster.service.dto.ArtistDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Artist} and its DTO {@link ArtistDTO}.
 */
@Mapper(componentModel = "spring")
public interface ArtistMapper extends EntityMapper<ArtistDTO, Artist> {}
