package com.travel_agency.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Entity representing a travel destination.
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Destinations")
public class Destination {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String location;

    @Column(length = 500)
    private String description;

    @Column(name = "rating_average")
    private float ratingAverage;

    @Column(name = "rating_number")
    private int ratingNumber;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @PrePersist
    protected void prePersist() {
        this.registrationDate = LocalDate.now();
    }

    public void calculateAverage() {
        if (reviews == null || reviews.isEmpty()) {
            this.ratingAverage = 0;
            this.ratingNumber = 0;
            return;
        }

        int totalRatings = reviews.stream()
                .mapToInt(Review::getRating)
                .sum();
        this.ratingNumber = reviews.size();
        this.ratingAverage = (float) totalRatings / ratingNumber;
    }
}
