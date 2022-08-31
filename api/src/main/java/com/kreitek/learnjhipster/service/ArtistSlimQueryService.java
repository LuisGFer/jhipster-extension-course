package com.kreitek.learnjhipster.service;

import com.kreitek.learnjhipster.domain.ArtistSlim;
import com.kreitek.learnjhipster.domain.ArtistSlim_;
import com.kreitek.learnjhipster.repository.ArtistSlimRepository;
import com.kreitek.learnjhipster.service.criteria.ArtistCriteria;
import com.kreitek.learnjhipster.service.criteria.ArtistSlimCriteria;
import com.kreitek.learnjhipster.service.dto.ArtistDTO;
import com.kreitek.learnjhipster.service.dto.ArtistSlimDTO;
import com.kreitek.learnjhipster.service.mapper.ArtistSlimMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import java.util.List;

/**
 * Service for executing complex queries for {@link com.kreitek.learnjhipster.domain.ArtistSlim} entities in the database.
 * The main input is a {@link ArtistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ArtistDTO} or a {@link Page} of {@link ArtistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ArtistSlimQueryService extends QueryService<ArtistSlim> {

    private final Logger log = LoggerFactory.getLogger(ArtistSlimQueryService.class);

    private final ArtistSlimRepository artistSlimRepository;
    private final ArtistSlimMapper artistSlimMapper;


    public ArtistSlimQueryService(ArtistSlimRepository artistSlimRepository, ArtistSlimMapper artistSlimMapper) {
        this.artistSlimRepository = artistSlimRepository;
        this.artistSlimMapper = artistSlimMapper;
    }

    /**
     * Return a {@link List} of {@link com.kreitek.learnjhipster.service.dto.ArtistSlimDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ArtistSlimDTO> findByCriteria(ArtistSlimCriteria criteria) {
        log.debug("find by criteria : {}", criteria.toString().replaceAll("[\n\r\t]", "_"));
        final Specification<ArtistSlim> specification = createSpecification(criteria);
        return artistSlimMapper.toDto(artistSlimRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ArtistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ArtistSlimDTO> findByCriteria(ArtistSlimCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria.toString().replaceAll("[\n\r\t]", "_"), page);
        final Specification<ArtistSlim> specification = createSpecification(criteria);
        return artistSlimRepository.findAll(specification, page).map(artistSlimMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ArtistSlimCriteria criteria) {
        log.debug("count by criteria : {}", criteria.toString().replaceAll("[\n\r\t]", "_"));
        final Specification<ArtistSlim> specification = createSpecification(criteria);
        return artistSlimRepository.count(specification);
    }

    /**
     * Function to convert {@link ArtistSlimCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ArtistSlim> createSpecification(ArtistSlimCriteria criteria) {
        Specification<ArtistSlim> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ArtistSlim_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ArtistSlim_.name));
            }
        }
        return specification;
    }

}
