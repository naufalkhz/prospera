package com.prospera.corebanking.dto.models.repos;

import com.prospera.corebanking.dto.models.entities.Tabungan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabunganRepo extends JpaRepository<Tabungan, Long> {
   Tabungan findByNoRekening(Long noRekening);
}
