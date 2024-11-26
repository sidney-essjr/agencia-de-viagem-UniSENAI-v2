package com.travel_agency.api.repository;

import com.travel_agency.api.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, UUID> {
}
