package com.peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1)
    private Long id;

    private String roleName;

    @ManyToMany(targetEntity = User.class,mappedBy = "roles", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
