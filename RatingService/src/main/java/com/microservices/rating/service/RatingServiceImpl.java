package com.microservices.rating.service;

import com.microservices.rating.entity.Rating;
import com.microservices.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> fetchAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> fetchRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> fetchRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }


    @Override
    public String deleteRatingByUserId(String userId) {
        ratingRepository.deleteById(userId);
        return "Rating has been deleted with the id : "+userId;
    }
}
