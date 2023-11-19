package com.microservices.rating.controller;

import com.microservices.rating.entity.Rating;
import com.microservices.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ratings")

public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating (@RequestBody Rating rating){
        return new ResponseEntity<>(ratingService.create(rating), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public List<Rating> fetchAllRatings (){
        return ratingService.fetchAll();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> fetchRatingsByUserId (@PathVariable("userId") String userId){
        return new ResponseEntity<>(ratingService.fetchRatingByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> fetchRatingsByHotelId (@PathVariable("hotelId") String hotelId){
        return  new ResponseEntity<>(ratingService.fetchRatingByHotelId(hotelId),HttpStatus.OK);
    }



    @DeleteMapping("/delete/{userId}")
    public String deleteRatingByUserId (@PathVariable String userId){
        return ratingService.deleteRatingByUserId(userId);
    }
}
