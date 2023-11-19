package com.rating.user.service.service;

import com.rating.user.service.entities.Hotel;
import com.rating.user.service.entities.Rating;
import com.rating.user.service.exceptions.ResourceNotFoundException;
import com.rating.user.service.external.service.HotelService;
import com.rating.user.service.repository.UserRepository;
import com.rating.user.service.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private HotelService hotelService;

    @Override
    public User saveUser(User user) {

       String  randomUserId = UUID.randomUUID().toString(); // generates Unique Id each time and converts it to string and we assign it to our userID.
       user.setUserId(randomUserId);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            // Fetch ratings of the user from the RatingService (microservice)
            ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), ArrayList.class);
            log.info("{} ", ratingsOfUser);
            user.setRatings(ratingsOfUser);
        }
        return users;
    }

    @Override
    public User getUserById(String userId) {
        // get user from the database with the help of UserRepository
      User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with this ID is not available at server !!" + userId));
      //fetch ratings of the above user from the RatingService(microservice)
//      ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:6062/users/"+user.getUserId(), ArrayList.class);

       Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
      log.info("{} ",ratingsOfUser);
      List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

      List<Rating> ratingList = ratings.stream().map(rating -> {
          System.out.println(rating.getHotelId());
         ResponseEntity<Hotel> newRatingWithHotelInfo =  restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/fetch/"+rating.getHotelId(), Hotel.class);
          Hotel hotel = newRatingWithHotelInfo.getBody();

//          Hotel hotel = hotelService.getHotel(rating.getHotelId()); //FeignClient
         rating.setHotel(hotel);
         return rating;

      }).collect(Collectors.toList());
      user.setRatings(ratingList);
      return user;

    }

    @Override
    public User updateUser(String userId, User user) {

        user.setUserId(userId);
        return userRepository.save(user);

    }

    @Override
    public String removeUserById(String userId) {
        userRepository.deleteById(userId);
        return " user Deleted";
    }
}
