package org.hospital_api.user_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Role {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "role")
    private List<User> users;
}
