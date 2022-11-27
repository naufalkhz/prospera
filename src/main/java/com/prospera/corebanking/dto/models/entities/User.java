package com.prospera.corebanking.dto.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 200, nullable = false)
    private String hak;

    @Column(length = 100)
    private long nikKaryawan;

//    @ManyToMany(mappedBy = "suppliers")
//    @JsonBackReference
//    private Set<Product> products;
}
