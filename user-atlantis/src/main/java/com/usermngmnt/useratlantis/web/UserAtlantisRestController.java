package com.usermngmnt.useratlantis.web;

import com.usermngmnt.useratlantis.entities.User;
import com.usermngmnt.useratlantis.respositories.UserAtlantisRepository;
import com.usermngmnt.useratlantis.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@CrossOrigin(origins = "*")
public class UserAtlantisRestController {
    UserAtlantisRepository userAtlantisRepository;

    public UserAtlantisRestController(UserService userService) {
        this.userService = userService;
    }

    UserService userService;

    UserAtlantisRestController(UserAtlantisRepository userAtlantisRepository){
        this.userAtlantisRepository = userAtlantisRepository;
    }

    @GetMapping(path = "/index")
    String users(){ return "users";}

    @GetMapping(path = "/users")
    List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping(path = "/user/{id}")
    User getUser(@PathVariable(value = "id") Long id){
        return userService.getUser(id);
    }

    @PostMapping(path = "users")
    User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(path = "/admin")
    void adminValidation(){

    }

    @RequestMapping(path = "/login")
    void loginValidation(){

    }
}
