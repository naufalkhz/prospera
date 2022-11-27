package com.prospera.corebanking.dto.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "officer")
public class Officer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private long nikKaryawan;

//    @Column(length = 50, nullable = false, unique = true)
    @Column(length = 50, nullable = false)
    private long nikKtp;

    @Column(length = 50, nullable = false)
    private String nama;

//    @Column(length = 50, nullable = false)
    @Transient
    @JsonIgnore
    private String email;

//    @Column(length = 50, nullable = false)
    @Transient
    @JsonIgnore
    private String password;

    @Column(length = 50, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;

    @Column(length = 50, nullable = false)
    private String tempatLahir;

    @Column(length = 50, nullable = false)
    private String alamat;

    @Column(length = 200, nullable = false)
    private String jabatan;

    @Column(length = 100)
    private String cabang;

    @Column
    private int status;

    public boolean isEmpty() {
        return false;
    }

//    @ManyToMany(mappedBy = "suppliers")
//    @JsonBackReference
//    private Set<Product> products;
}
