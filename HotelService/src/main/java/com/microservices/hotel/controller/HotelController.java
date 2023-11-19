package com.microservices.hotel.controller;

import com.microservices.hotel.entity.Hotel;
import com.microservices.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public List<Hotel> fetchAllHotels(){
        return hotelService.getAll();
    }

//    @PreAuthorize("hasAuthority('SCOPE_internal')|| hasAuthority('Admin')")
    @GetMapping("/fetch/{hotelId}")
    public ResponseEntity<Optional<Hotel>> fetchById(@PathVariable("hotelId") String id){
        return new ResponseEntity<>(hotelService.getById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Hotel> updateHotelById(@PathVariable("userId") String id,@RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.updateHotel(id,hotel),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public String  deleteHotelById(@PathVariable("userId") String id ){
        hotelService.deleteHotel(id);
        return "hotel deleted.";
    }
}
