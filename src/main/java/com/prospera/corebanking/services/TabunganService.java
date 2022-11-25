package com.prospera.corebanking.services;

import com.prospera.corebanking.dto.models.entities.Tabungan;
import com.prospera.corebanking.dto.models.repos.TabunganRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TabunganService {
    @Autowired
    private TabunganRepo tabunganRepo;

    public Tabungan findByNoRekening (Long noRekening){
        Tabungan tabungan = tabunganRepo.findByNoRekening(noRekening);
//        if (!supplier.isPresent()){
//            return null;
//        }
        return tabungan;
    }

    public Tabungan update (Tabungan tabungan){
        return tabunganRepo.save(tabungan);
    }

    public Iterable<Tabungan> findAll(){
        return tabunganRepo.findAll();
    }

}
