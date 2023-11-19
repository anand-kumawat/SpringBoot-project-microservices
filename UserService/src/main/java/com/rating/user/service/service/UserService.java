package com.rating.user.service.service;

import com.rating.user.service.entities.User;

import java.util.List;

public interface UserService {

    // create User
    User saveUser(User user );

    // get all user
    List<User> getAllUser();

    // get a user by ID
    User getUserById(String userId);

    // update a user by its Id
    User updateUser(String userId, User user);

    // delete a user



    String removeUserById(String userId);


}
