package com.brainwired.User.Service;

import com.brainwired.User.Request.UserRequest;
import com.brainwired.User.Entity.User;
import com.brainwired.User.Exception.UserNotFoundException;

import java.util.List;


public interface UserService {

    public User addUser(UserRequest userRequest);

    User getUserForGivenUserId(String userId) throws UserNotFoundException;

    List<User> getAllUsers();

    User updateTheUser(UserRequest userRequest) throws UserNotFoundException;

    User deleteTheUser(String userId) throws UserNotFoundException;
}
