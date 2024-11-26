package com.travel_agency.api.web.controller;

import com.travel_agency.api.entity.Reservation;
import com.travel_agency.api.service.ReservationService;
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
@RequestMapping("api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping()
    public ResponseEntity<Reservation> create(@RequestBody Reservation data) {
        Reservation reservation = this.reservationService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable UUID id) {
        Reservation reservation = this.reservationService.findById(id);
        return ResponseEntity.ok().body(reservation);
    }

    @GetMapping()
    public ResponseEntity<Page<Reservation>> findAll(@RequestParam int page,
                                                     @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> packages = this.reservationService.findAll(pageable);
        return ResponseEntity.ok().body(packages);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Reservation> update(@PathVariable UUID id, Reservation data) {
        Reservation reservation = this.reservationService.update(id, data);
        return ResponseEntity.ok().body(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
