package com.example.hotel_thymeleaf_security.entity.hotel.moreOptions.moreOptions;

import com.example.hotel_thymeleaf_security.entity.hotel.HotelEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.hotel_thymeleaf_security.entity.BaseEntity;

@Entity(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review extends BaseEntity {
    private String username;
    private int rating;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
    @Column(length = 1000)
    private String comment;
}
