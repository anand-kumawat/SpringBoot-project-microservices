package com.microservices.hotel.service;

import com.microservices.hotel.entity.Hotel;
import com.microservices.hotel.exceptions.ResourceNotFoundException;
import com.microservices.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> getById(String id) {
        return Optional.ofNullable(hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("hotel with the given id is not found.")));
    }

    @Override
    public Hotel updateHotel( String id, Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public String deleteHotel(String id) {
        hotelRepository.deleteById(id);
        return "Deleted." ;
    }
}
