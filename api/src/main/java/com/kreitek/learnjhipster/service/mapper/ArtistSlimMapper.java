package com.kreitek.learnjhipster.service.mapper;


import com.kreitek.learnjhipster.domain.ArtistSlim;
import com.kreitek.learnjhipster.service.dto.ArtistSlimDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link ArtistSlim} and its Slim DTO {@link ArtistSlimDTO}.
 */
@Mapper(componentModel = "spring")
public interface ArtistSlimMapper extends EntityMapper<ArtistSlimDTO, ArtistSlim> {
}
