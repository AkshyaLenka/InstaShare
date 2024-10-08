package com.instashare.social.contoller;

import com.instashare.social.exceptions.UserException;
import com.instashare.social.model.User;
import com.instashare.social.repository.UserRepository;
import com.instashare.social.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws UserException {
        User user = userService.findUserById(id);
        return user;
    }

//
//    @PostMapping("/users")
//    public User createUser(@RequestBody @NotNull User user){
//        User saveUser = userService.registerUser(user);
//
//        return saveUser;
//    }

    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user,reqUser.getId());
        return updatedUser;

    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        User user= userService.followUser(reqUser.getId(),userId2);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return users;
    }
    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }

//    @DeleteMapping("users/{userId}")
//    public String deleteUser(@PathVariable Integer userId) throws Exception {
//
//        Optional<User> user = userRepository.findById(userId);
//        if(user.isEmpty()){
//            throw new Exception("User Not Exist with ID: "+userId);
//        }
//
//        else{
//            userRepository.deleteById(userId);
//        }
//
//        return "User Deleted with ID: "+userId;
//    }

}
