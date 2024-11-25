package com.travel_agency.api.repository;

import com.travel_agency.api.entity.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DestinationRepository extends JpaRepository<Destination, UUID> {

    /**
     * Find a list of destinations by name.
     * @param name The name of the destination to search for.
     * @return A pageable list of destinations with the given name.
     */
    Page<Destination> findByName(String name, Pageable pageable);

    /**
     * Find a list of destinations by location.
     * @param location The name of the destination to search for.
     * @return A pageable list of destinations with the given location.
     */
    Page<Destination> findByLocation(String location, Pageable pageable);


}
