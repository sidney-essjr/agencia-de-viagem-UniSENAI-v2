package com.travel_agency.api.web.controller;

import com.travel_agency.api.entity.TravelPackage;
import com.travel_agency.api.service.TravelPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "api/v1/packages")
public class TravelPackageController {
    private final TravelPackageService travelPackageService;

    @PostMapping()
    public ResponseEntity<TravelPackage> create(@RequestBody TravelPackage data) {
        TravelPackage travelPackage = this.travelPackageService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(travelPackage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPackage> findById(@PathVariable UUID id) {
        TravelPackage travelPackage = this.travelPackageService.findById(id);
        return ResponseEntity.ok().body(travelPackage);
    }

    @GetMapping()
    public ResponseEntity<Page<TravelPackage>> findAll(@RequestParam int page,
                                                       @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TravelPackage> packages = this.travelPackageService.findAll(pageable);
        return ResponseEntity.ok().body(packages);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TravelPackage> update(@PathVariable UUID id, TravelPackage data) {
        TravelPackage travelPackage = this.travelPackageService.update(id, data);
        return ResponseEntity.ok().body(travelPackage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.travelPackageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
