package com.alphadevs.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.JoinType;

import com.alphadevs.com.service.dto.ExUserCriteria;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.alphadevs.com.domain.Location;
import com.alphadevs.com.domain.*; // for static metamodels
import com.alphadevs.com.repository.LocationRepository;
import com.alphadevs.com.service.dto.LocationCriteria;

/**
 * Service for executing complex queries for {@link Location} entities in the database.
 * The main input is a {@link LocationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Location} or a {@link Page} of {@link Location} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LocationQueryService extends QueryService<Location> {

    private final Logger log = LoggerFactory.getLogger(LocationQueryService.class);

    private final LocationRepository locationRepository;
    private final UserService userService;
    private final ExUserQueryService exUserQueryService;

    public LocationQueryService(LocationRepository locationRepository, UserService userService, ExUserQueryService exUserQueryService) {
        this.locationRepository = locationRepository;
        this.userService = userService;
        this.exUserQueryService = exUserQueryService;
    }

    /**
     * Return a {@link List} of {@link Location} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Location> findByCriteria(LocationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Location> specification = createSpecification(criteria);
        return locationRepository.findAll(specification);
    }

    /**
     * Return a {@link List} of {@link Location} which matches the criteria from the database.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Location> findByLoggedUser(LocationCriteria locationCriteria) {
        log.debug("find by loggedUser : {}",locationCriteria);
        List<Location> filteredLocationList = new ArrayList<>();
        final Optional<User> loggedUser = userService.getUserWithAuthorities();
        if(loggedUser.isPresent()){
            Long userID = loggedUser.get().getId();
            LongFilter longFilterUserID = new LongFilter();
            longFilterUserID.setEquals(userID);

            ExUserCriteria exUserCriteria = new ExUserCriteria();
            exUserCriteria.setRelatedUserId(longFilterUserID);
            final List<ExUser> exUserList = exUserQueryService.findByCriteria(exUserCriteria);
            if(exUserList != null && !exUserList.isEmpty() && exUserList.get(0) != null && exUserList.get(0).getLocations() != null ){
                if(locationCriteria.getCompanyId() != null){
                    for ( Location location : exUserList.get(0).getLocations()){
                        if(location.getCompany() != null && location.getCompany().getId() == locationCriteria.getCompanyId().getEquals()){
                            filteredLocationList.add(location);
                        }
                    }
                }else{
                    filteredLocationList = new ArrayList<>(exUserList.get(0).getLocations());
                }
            }
        }else{
            log.debug("user not logged in : {}",locationCriteria);
        }

        return filteredLocationList;

    }

    /**
     * Return a {@link Page} of {@link Location} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Location> findByCriteria(LocationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Location> specification = createSpecification(criteria);
        return locationRepository.findAll(specification, page);
    }




    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LocationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Location> specification = createSpecification(criteria);
        return locationRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Location> createSpecification(LocationCriteria criteria) {
        Specification<Location> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Location_.id));
            }
            if (criteria.getLocationCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationCode(), Location_.locationCode));
            }
            if (criteria.getLocationName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationName(), Location_.locationName));
            }
            if (criteria.getLocationProfMargin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLocationProfMargin(), Location_.locationProfMargin));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Location_.isActive));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildSpecification(criteria.getCompanyId(),
                    root -> root.join(Location_.company, JoinType.LEFT).get(Company_.id)));
            }
            if (criteria.getUsersId() != null) {
                specification = specification.and(buildSpecification(criteria.getUsersId(),
                    root -> root.join(Location_.users, JoinType.LEFT).get(ExUser_.id)));
            }
        }
        return specification;
    }
}
