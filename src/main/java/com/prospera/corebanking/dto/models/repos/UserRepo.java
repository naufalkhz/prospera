package com.prospera.corebanking.dto.models.repos;

import com.prospera.corebanking.dto.models.entities.Tabungan;
import com.prospera.corebanking.dto.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
