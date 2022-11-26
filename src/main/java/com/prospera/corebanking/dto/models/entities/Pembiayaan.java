package com.prospera.corebanking.dto.models.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "pembiayaan")
public class Pembiayaan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private long nikKtp;

    @Column(length = 50, nullable = false)
    private String nama;

    @Column(length = 1, nullable = false)
    private int status;

    @Column(length = 50, nullable = false)
    private long jumlahPembiayaan;

    @Column(length = 50, nullable = false)
    private long jumlahHarusBayar;

    @Column(length = 50, nullable = false)
    private long jumlahHarusBayarBulan;

    @Column(length = 50, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalPembiayaan;

    @Column(length = 50, nullable = false)
    private int tenor;




//    @Column(length = 50, nullable = false, unique = true)

//    @ManyToMany(mappedBy = "suppliers")
//    @JsonBackReference
//    private Set<Product> products;
}
