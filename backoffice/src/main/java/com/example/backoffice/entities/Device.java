package com.example.backoffice.entities;

import com.example.backoffice.enums.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne @JoinColumn(name = "userId")
    private User user;

    String name;
    String macAddress;
    @Enumerated(EnumType.STRING)
    DeviceType type;
}
