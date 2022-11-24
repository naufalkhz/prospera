package com.prospera.corebanking.dto.models.repos;

import com.prospera.corebanking.dto.models.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficerRepo extends JpaRepository<Officer, Long> {
   Officer findByNikKaryawan(Long nikKaryawan);
}
