package com.microservices.hotel.service;

import com.microservices.hotel.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    Hotel createHotel(Hotel hotel);
    List<Hotel> getAll();
    Optional<Hotel> getById(String id);
    Hotel updateHotel( String id, Hotel hotel);
    String deleteHotel(String id);
}
