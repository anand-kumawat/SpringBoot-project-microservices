package com.rating.user.service.controllers;

import com.rating.user.service.service.UserService;
import com.rating.user.service.entities.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
//    public ResponseEntity<User> createUser (@RequestBody User user){
//        User user1 = userService.saveUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
//
//    }

    public ResponseEntity<User> createUser (@RequestBody User user){

        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);

    }

    @RequestMapping( path = "/get/{userId}",method = RequestMethod.GET)
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){

        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
        log.info("Fallback is executed due to this problem : ",ex.getMessage());
        User user = User.builder()
                .name("Demo")
                .email("demo@gmail.com")
                .about("fvbdfbdfbd")
                .userId("5555")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping
//    public ResponseEntity<List<User>> getAllUser(){
//        List<User> allUsers = userService.getAllUser();
//        return ResponseEntity.ok(allUsers);
//
//    }

    public List<User> getAllUser(){
        log.info("we are seeing all the user at once!");
        List<User> allUsers = userService.getAllUser();
        return  allUsers;

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeUserById(@PathVariable("userId") String userId){
        log.info("inside the delete method");
        return new ResponseEntity<>(userService.removeUserById(userId),HttpStatus.OK);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser( @PathVariable("userId") String userId,@RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(userId, user),HttpStatus.OK);

    }


}
