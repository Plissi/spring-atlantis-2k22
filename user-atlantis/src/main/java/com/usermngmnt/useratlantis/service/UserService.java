package com.usermngmnt.useratlantis.service;

import com.usermngmnt.useratlantis.entities.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUser(Long id);
    User loadUser(String username);
    List<User> findAllUsers();
    List<User> loadUsers();
}
