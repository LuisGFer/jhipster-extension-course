package com.kreitek.learnjhipster.domain.song.impl;

import com.kreitek.learnjhipster.domain.song.SongCheckerFilterByAlbumAppliedGuard;
import com.kreitek.learnjhipster.service.criteria.SongCriteria;
import com.kreitek.learnjhipster.web.rest.errors.BadRequestAlertException;
import org.springframework.stereotype.Service;

@Service
public class SongCheckerFilterByAlbumAppliedGuardImpl implements SongCheckerFilterByAlbumAppliedGuard {
    @Override
    public void check(SongCriteria criteria) {
        if (criteria.getAlbumId() == null) {
            throw new BadRequestAlertException("Not album defined for this request", null, "Not album defined for this request");
        }
    }
}
