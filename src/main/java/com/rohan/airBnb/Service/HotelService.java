package com.rohan.airBnb.Service;

import com.rohan.airBnb.dto.HotelDTO;
import com.rohan.airBnb.dto.HotelInfoDto;
import com.rohan.airBnb.dto.HotelInfoRequestDto;

import java.util.List;


public interface HotelService {
    public HotelDTO createHotel(HotelDTO hotelDTO) ;

    public HotelDTO getHotelById(Long id) ;

    public HotelDTO updateHotelById(Long HotelId ,HotelDTO hotelDTO) ;

    public void activateHotel(Long HotelId) ;

    public void deactivateHotel(Long HotelId) ;

    public void DeleteHotelById(Long HotelId ) ;

    public void ExistsById(Long id) ;

    List<HotelDTO> getAllHotels();

    HotelInfoDto getHotelInfoById(Long hotelId, HotelInfoRequestDto hotelInfoRequestDto);
}
