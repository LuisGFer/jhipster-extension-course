package com.kreitek.learnjhipster.repository;

import com.kreitek.learnjhipster.domain.Artist;
import com.kreitek.learnjhipster.domain.ArtistSlim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Artist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArtistSlimRepository extends JpaRepository<ArtistSlim, Long>, JpaSpecificationExecutor<ArtistSlim> {
}
