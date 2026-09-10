package com.rohan.airBnb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDto {
    private HotelDTO hotel;
    private List<RoomPriceResponseDto> rooms;
}
