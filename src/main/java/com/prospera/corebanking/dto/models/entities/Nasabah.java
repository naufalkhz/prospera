package com.prospera.corebanking.dto.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "nasabah")
@DynamicUpdate
public class Nasabah implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nama;

    @Column(length = 50, nullable = false)
    private long nikKtp;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String noHP;

    @Column(length = 50, nullable = false)
    private String pekerjaan;

    @Column(length = 250, nullable = false)
    private String alamat;

    @Column(length = 1, nullable = false)
    private int flagWarungTepat;

    @Column(length = 50, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalBuat;

//    @Column(length = 50, nullable = false, unique = true)

//    @ManyToMany(mappedBy = "suppliers")
//    @JsonBackReference
//    private Set<Product> products;
}
