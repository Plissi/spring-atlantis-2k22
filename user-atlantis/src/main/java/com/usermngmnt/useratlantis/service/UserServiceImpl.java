package com.usermngmnt.useratlantis.service;

import com.usermngmnt.useratlantis.entities.User;
import com.usermngmnt.useratlantis.respositories.UserAtlantisRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    UserAtlantisRepository userAtlantisRepository;

    public UserServiceImpl(UserAtlantisRepository userAtlantisRepository, PasswordEncoder passwordEncoder) {
        this.userAtlantisRepository = userAtlantisRepository;
        this.passwordEncoder = passwordEncoder;
    }

    PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        String pw = user.getPassword();
        user.setPassword(passwordEncoder.encode(pw));
        return userAtlantisRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userAtlantisRepository.findById(id).get();
    }

    @Override
    public User loadUser(String username) {
        return userAtlantisRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userAtlantisRepository.findAll();
    }

    @Override
    public List<User> loadUsers() {
        return null;
    }
}
