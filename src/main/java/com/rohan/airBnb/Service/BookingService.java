package com.rohan.airBnb.Service;


import com.rohan.airBnb.Entity.enums.BookingStatus;
import com.rohan.airBnb.dto.BookingDTO;
import com.rohan.airBnb.dto.BookingRequest;
import com.rohan.airBnb.dto.HotelReportDto;
import com.stripe.model.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingDTO initializeBooking(BookingRequest bookingRequest);

    BookingDTO addGuest(Long BookingId , List<Long> guestList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    BookingStatus getBookingStatus(Long bookingId);

    List<BookingDTO> getAllBookingsByHotelId(Long hotelId);

    HotelReportDto getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDTO> getMyBookings();
}
