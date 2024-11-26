package com.travel_agency.api.service;

import com.travel_agency.api.entity.Reservation;
import com.travel_agency.api.entity.TravelPackage;
import com.travel_agency.api.repository.ReservationRepository;
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
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation create(Reservation data) {
        return this.reservationRepository.save(data);
    }

    @Transactional(readOnly = true)
    public Reservation findById(UUID id) {
        return this.reservationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("The Reservation id %s was not " +
                        "found", id)));
    }

    @Transactional(readOnly = true)
    public Page<Reservation> findAll(Pageable pageable) {
        return this.reservationRepository.findAll(pageable);
    }

    @Transactional
    public Reservation update(UUID id, Reservation data) {
        Reservation reservation = this.findById(id);
        BeanUtils.copyProperties(data, reservation, Utils.getNullPropertyNames(data));
        return this.reservationRepository.save(reservation);
    }

    @Transactional
    public void delete(UUID id) {
        Reservation reservation = this.findById(id);
        this.reservationRepository.delete(reservation);
    }
}
