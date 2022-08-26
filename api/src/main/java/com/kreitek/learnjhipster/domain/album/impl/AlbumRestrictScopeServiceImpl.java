package com.kreitek.learnjhipster.domain.album.impl;

import com.kreitek.learnjhipster.domain.album.AlbumRestrictScopeService;
import com.kreitek.learnjhipster.security.AuthoritiesConstants;
import com.kreitek.learnjhipster.security.SecurityUtils;
import com.kreitek.learnjhipster.service.criteria.AlbumCriteria;
import org.springframework.stereotype.Service;
import tech.jhipster.service.filter.LongFilter;

@Service
public class AlbumRestrictScopeServiceImpl implements AlbumRestrictScopeService {
    public static final long POP_STYLE_ID = 1202L;

    @Override
    public AlbumCriteria restrict(AlbumCriteria criteria) {

        boolean currentUserIsEditor = SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.EDITOR);
        if (currentUserIsEditor) {
            LongFilter filterPopStyle = new LongFilter();
            filterPopStyle.setEquals(POP_STYLE_ID);
            criteria.setStyleId(filterPopStyle);
        }
        return criteria;
    }
}
