package com.instashare.social.contoller;

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
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        User user = userService.findUserById(id);
        return user;
    }


    @PostMapping("/users")
    public User createUser(@RequestBody @NotNull User user){
        User saveUser = userService.registerUser(user);

        return saveUser;
    }

    @PutMapping("/api/users/{userId}")
    public User updateUser(@RequestBody User updateUserRequest, @PathVariable Integer userId) throws Exception {
        User updateUser = userService.updateUser(updateUserRequest,userId);
        return updateUser;

    }

    @PutMapping("/api/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user= userService.followUser(userId1,userId2);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return users;
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
