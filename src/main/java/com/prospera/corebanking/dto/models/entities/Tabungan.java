package com.prospera.corebanking.dto.models.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tabungan")
@DynamicUpdate
public class Tabungan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(length = 50, nullable = false)
//    private long nikKtp;

    @Column(length = 50, nullable = false)
    private long noRekening;

    @Column(length = 50, nullable = false)
    private long saldo;




//    @Column(length = 50, nullable = false, unique = true)

//    @ManyToMany(mappedBy = "suppliers")
//    @JsonBackReference
//    private Set<Product> products;
}
