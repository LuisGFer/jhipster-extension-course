package com.kreitek.learnjhipster.domain.album;

import com.kreitek.learnjhipster.service.criteria.AlbumCriteria;
import org.springframework.stereotype.Service;

@Service
public interface AlbumRestrictScopeService {
    AlbumCriteria restrict(AlbumCriteria criteria);
}
