package com.rohan.airBnb.dto;

import com.rohan.airBnb.Entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPriceDTO {
    private Hotel hotel;

    private Double price;
}
