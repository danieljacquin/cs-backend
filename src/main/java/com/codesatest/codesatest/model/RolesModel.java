package com.codesatest.codesatest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RolesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "rolesModel")
    @JsonIgnore
    private List<UsersModel> usersModels;
}
