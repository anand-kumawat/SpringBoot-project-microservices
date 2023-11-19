package com.rating.user.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "micro-users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "ID" )
    private String userId;
    @Column(name = "NAME", length = 20)
    private String name;
    @Column(name = "Email")

    private String email;
    @Column(name="ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings;


}
