package com.travel_agency.api.service;

import com.travel_agency.api.entity.TravelPackage;
import com.travel_agency.api.repository.TravelPackageRepository;
import com.travel_agency.api.web.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;

    @Transactional
    public TravelPackage create(TravelPackage data) {
        return this.travelPackageRepository.save(data);
    }

    @Transactional
    public TravelPackage findById(UUID id) {
        return this.travelPackageRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("The Travel Package id %s was not found", id)));
    }

    @Transactional
    public Page<TravelPackage> findAll(Pageable pageable) {
        return this.travelPackageRepository.findAll(pageable);
    }

    @Transactional
    public TravelPackage update(UUID id, TravelPackage data) {
        TravelPackage travelPackage = this.findById(id);
        BeanUtils.copyProperties(data, travelPackage, Utils.getNullPropertyNames(data));
        return this.travelPackageRepository.save(travelPackage);
    }

    @Transactional
    public void delete(UUID id) {
        TravelPackage travelPackage = this.findById(id);
        this.travelPackageRepository.delete(travelPackage);
    }
}
