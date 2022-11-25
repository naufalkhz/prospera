package com.prospera.corebanking.dto.models.repos;

import com.prospera.corebanking.dto.models.entities.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasabahRepo extends JpaRepository<Nasabah, Long> {
   Nasabah findByNikKtp(Long nikKtp);
}
