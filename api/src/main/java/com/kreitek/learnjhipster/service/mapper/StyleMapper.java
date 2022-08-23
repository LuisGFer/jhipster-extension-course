package com.kreitek.learnjhipster.service.mapper;

import com.kreitek.learnjhipster.domain.Style;
import com.kreitek.learnjhipster.service.dto.StyleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Style} and its DTO {@link StyleDTO}.
 */
@Mapper(componentModel = "spring")
public interface StyleMapper extends EntityMapper<StyleDTO, Style> {}
