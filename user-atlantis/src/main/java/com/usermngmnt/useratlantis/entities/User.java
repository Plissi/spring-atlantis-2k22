package com.usermngmnt.useratlantis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.usermngmnt.useratlantis.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
}
