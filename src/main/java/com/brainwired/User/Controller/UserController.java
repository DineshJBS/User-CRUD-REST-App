package com.brainwired.User.Controller;

import com.brainwired.User.Config.ResponseHandler;
import com.brainwired.User.Exception.UserNotFoundException;
import com.brainwired.User.Request.UserRequest;
import com.brainwired.User.Entity.User;
import com.brainwired.User.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/brainwired/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserRequest userRequest){

        log.info("Incoming UserRequest : " + userRequest);

        User user = userService.addUser(userRequest);
        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setResponseCode(201);
        responseHandler.setResponseMessage("User with userId " + userRequest.getUserId() +" created and saved successfully!");
        return new ResponseEntity<>(responseHandler, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getUserForGivenUserId(@PathVariable String userId) throws UserNotFoundException {

        if(userId == null){
            throw new UserNotFoundException("userId cannot be null");
        }

        User user =  userService.getUserForGivenUserId(userId);

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setResponseCode(200);
        responseHandler.setResponseMessage("User retrieved successfully!");
        responseHandler.setUser(user);

        return new ResponseEntity<>(responseHandler, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers(){
        List<User> users = userService.getAllUsers();
        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setResponseCode(200);
        responseHandler.setResponseMessage("Users retrieved successfully!");
        responseHandler.setUsers(users);

        return new ResponseEntity<>(responseHandler, HttpStatus.OK);

    }

    @PutMapping("/user")
    public ResponseEntity<Object> updateUserData(@Valid @RequestBody UserRequest userRequest) throws UserNotFoundException {

        log.info("Update User request : " + userRequest);

        User user =  userService.updateTheUser(userRequest);

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setResponseCode(200);
        responseHandler.setResponseMessage("User updated successfully!");
        responseHandler.setUser(user);

        return new ResponseEntity<>(responseHandler, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId) throws UserNotFoundException {

        if(userId == null){
            throw new UserNotFoundException("userId cannot be null");
        }

        User user =  userService.deleteTheUser(userId);

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setResponseCode(200);
        responseHandler.setResponseMessage("User retrieved successfully!");
        responseHandler.setUser(user);

        return new ResponseEntity<>(responseHandler, HttpStatus.OK);
    }



}
