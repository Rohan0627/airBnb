package com.rohan.airBnb.Service;

import com.rohan.airBnb.Entity.Room;
import com.rohan.airBnb.dto.HotelPriceResponseDTO;
import com.rohan.airBnb.dto.HotelSearchRequest;
import com.rohan.airBnb.dto.InventoryDto;
import com.rohan.airBnb.dto.UpdateInventoryRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {

    public void initializeRoomForAYear(Room room ) ;

    public void deleteAllInventories(Room room) ;

    Page<HotelPriceResponseDTO> searchHotel(HotelSearchRequest hotelSearchRequest);

    List<InventoryDto> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);
}
