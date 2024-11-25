package com.travel_agency.api.repository;

import com.travel_agency.api.entity.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, UUID> {

    /**
     * Find a list of destinations by name or location.
     * @param name The name of the destination to search for.
     * @param location The location of the destination to search for.
     * @param pageable The pagination configuration.
     * @return A pageable list of destinations with the given name or location.
     */
    Page<Destination> findByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(
            String name, String location, Pageable pageable);


}
