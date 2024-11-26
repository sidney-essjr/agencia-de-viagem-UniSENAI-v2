package com.travel_agency.api.web.dto;

import com.travel_agency.api.entity.Review;
import com.travel_agency.api.entity.TravelPackage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinationResponseDto {
    private String name;

    private String location;

    private String description;

    private float ratingAverage;

    private int ratingNumber;

    private List<Review> reviews;

    private LocalDate registrationDate;
}
