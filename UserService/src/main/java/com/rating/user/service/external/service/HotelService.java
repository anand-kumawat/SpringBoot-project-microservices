package com.rating.user.service.external.service;

import com.rating.user.service.entities.Hotel;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/fetch/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String id);

}
