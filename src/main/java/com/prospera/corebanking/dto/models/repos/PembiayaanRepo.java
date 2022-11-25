package com.prospera.corebanking.dto.models.repos;

import com.prospera.corebanking.dto.models.entities.Officer;
import com.prospera.corebanking.dto.models.entities.Pembiayaan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembiayaanRepo extends JpaRepository<Pembiayaan, Long> {
   //Officer findByNikKaryawan(Long nikKaryawan);
}
