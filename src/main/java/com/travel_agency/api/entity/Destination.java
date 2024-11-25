package com.travel_agency.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity representing a travel destination.
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter @ToString
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
    private Review reviews;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @PrePersist
    protected void prePersist() {
            this.registrationDate = LocalDate.now();
    }

}
