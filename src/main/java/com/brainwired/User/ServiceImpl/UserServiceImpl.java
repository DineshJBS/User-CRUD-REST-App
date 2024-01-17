package com.brainwired.User.ServiceImpl;

import com.brainwired.User.Request.UserRequest;
import com.brainwired.User.Entity.User;
import com.brainwired.User.Exception.UserNotFoundException;
import com.brainwired.User.Repo.UserRepo;
import com.brainwired.User.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User addUser(UserRequest userRequest) {

        User user = new User();
        user.setUserId(userRequest.getUserId());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setAddress(userRequest.getAddress());
        user.setSoftDelete(0);

        log.info("User Entity before saving to DB :  " + user);

        try{
            userRepo.save(user);
            log.info("User saved successfully");
        }catch (Exception e){
            log.error("Exception occurred while saving User");
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserForGivenUserId(String userId) throws UserNotFoundException {
        Optional<User> userOptional =  userRepo.findByUserId(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("User not found for " + userId);

        if(userOptional.get().getSoftDelete() == 1){
            throw new UserNotFoundException("User not found for " + userId);
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll().stream()
                .filter(u -> u.getSoftDelete() == 0)
                .toList();
    }

    @Override
    public User updateTheUser(UserRequest userRequest) throws UserNotFoundException {
        Optional<User> userOptional =  userRepo.findByUserId(userRequest.getUserId());
        if(userOptional.isEmpty()) throw new UserNotFoundException("User not found for " + userRequest.getUserId());

        if(userOptional.get().getSoftDelete() == 1){
            throw new UserNotFoundException("User not found for " + userRequest.getUserId());
        }

        User tempUser = userOptional.get();
        tempUser.setFirstName(userRequest.getFirstName());
        tempUser.setLastName(userRequest.getLastName());
        tempUser.setDateOfBirth(userRequest.getDateOfBirth());
        tempUser.setAddress(userRequest.getAddress());

        return userRepo.save(tempUser);

    }

    @Override
    public User deleteTheUser(String userId) throws UserNotFoundException {

        Optional<User> userOptional =  userRepo.findByUserId(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("User not found for " + userId);

        if(userOptional.get().getSoftDelete() == 1){
            throw new UserNotFoundException("User not found for " +userId);
        }

        User tempUser = userOptional.get();
        tempUser.setSoftDelete(1);
        return userRepo.save(tempUser);
    }
}
