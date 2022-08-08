package com.codesatest.codesatest.model;

import com.codesatest.codesatest.enums.statusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private statusEnum state;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    @JsonIgnore
    private RolesModel rolesModel;

}
