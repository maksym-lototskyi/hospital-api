package org.hospital_api.user_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    @ManyToOne
    private Role role;
}
