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
@Table(name = "Destinations")
public class Destination {

    @Id
    @GeneratedValue
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

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @PrePersist
    protected void prePersist() {
        if(this.registrationDate == null) {
            this.registrationDate = LocalDate.now();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
