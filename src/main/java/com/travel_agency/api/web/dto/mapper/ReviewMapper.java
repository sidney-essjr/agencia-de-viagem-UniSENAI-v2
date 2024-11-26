package com.travel_agency.api.web.dto.mapper;

import com.travel_agency.api.entity.Review;
import com.travel_agency.api.web.dto.ReviewCreateDto;
import com.travel_agency.api.web.dto.ReviewResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewMapper {

    public static Review toReview(ReviewCreateDto dto) {
        return new ModelMapper().map(dto, Review.class);
    }

    public static ReviewResponseDto toDto(Review review) {
        return new ModelMapper().map(review, ReviewResponseDto.class);
    }

    public static List<ReviewResponseDto> toListDto(Page<Review> reviews) {
        return reviews.stream().map(ReviewMapper::toDto).collect(Collectors.toList());
    }
}
