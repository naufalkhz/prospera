package com.prospera.corebanking.dto.models.repos;

import com.prospera.corebanking.dto.models.entities.Tabungan;
import com.prospera.corebanking.dto.models.entities.TabunganHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabunganHistoryRepo extends JpaRepository<TabunganHistory, Long> {
   Iterable<TabunganHistory> findAllByNoRekening(Long noRekening);
}
