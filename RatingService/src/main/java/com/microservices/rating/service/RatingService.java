package com.microservices.rating.service;


import com.microservices.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);
    List<Rating> fetchAll();
    List<Rating> fetchRatingByUserId(String userId);
    List<Rating> fetchRatingByHotelId(String hotelId);
    String deleteRatingByUserId(String userId);
}
