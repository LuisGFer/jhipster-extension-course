package com.kreitek.learnjhipster.domain.song;

import com.kreitek.learnjhipster.service.criteria.SongCriteria;
import org.springframework.stereotype.Service;

@Service
public interface SongCheckerFilterByAlbumAppliedGuard {
    void check(SongCriteria criteria);
}
