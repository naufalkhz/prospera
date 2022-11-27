package com.prospera.corebanking.dto.models.repos;

import com.prospera.corebanking.dto.models.entities.Officer;
import com.prospera.corebanking.dto.models.entities.Pembiayaan;
import com.prospera.corebanking.dto.models.entities.Tabungan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembiayaanRepo extends JpaRepository<Pembiayaan, Long> {
    Iterable<Pembiayaan> findAllByNikKtp(Long nikKtp);
    Pembiayaan findByNoPembiayaan(Long noPembiayaan);
}
