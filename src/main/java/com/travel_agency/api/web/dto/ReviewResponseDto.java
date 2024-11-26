package com.travel_agency.api.web.dto;

import com.travel_agency.api.entity.Review;
import jakarta.persistence.Column;
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
public class ReviewResponseDto {
    private int rating;

    private String description;

    private LocalDate evaluateDate;
}
