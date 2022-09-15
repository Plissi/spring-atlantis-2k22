package com.usermngmnt.useratlantis.respositories;

import com.usermngmnt.useratlantis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestController
@CrossOrigin(origins = "*")
public interface UserAtlantisRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
