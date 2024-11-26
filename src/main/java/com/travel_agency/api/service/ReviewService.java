package com.travel_agency.api.service;

import com.travel_agency.api.entity.Destination;
import com.travel_agency.api.entity.Review;
import com.travel_agency.api.repository.ReviewRepository;
import com.travel_agency.api.web.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review create(Destination destination, Review data) {
        data.setDestination(destination);
        return this.reviewRepository.save(data);
    }

    @Transactional(readOnly = true)
    public Review findById(UUID id) {
        return this.reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("The Review id %s was not found", id)));
    }

    @Transactional
    public Review update(UUID id, Review data) {
        Review review = this.findById(id);
        BeanUtils.copyProperties(data, review, Utils.getNullPropertyNames(data));
        return this.reviewRepository.save(review);
    }

    @Transactional
    public void delete(UUID id) {
        Review review = this.findById(id);
        this.reviewRepository.delete(review);
    }
}
