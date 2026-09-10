package com.rohan.airBnb.dto;

import com.rohan.airBnb.Entity.HotelContactInfo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {

    private Long id;
    private String name;
    private String city;

    private String[] photos ;

    private String[] amenities ;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean active;

    private HotelContactInfo hotelContactInfo ;
}
