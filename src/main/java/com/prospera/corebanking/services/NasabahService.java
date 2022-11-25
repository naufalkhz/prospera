package com.prospera.corebanking.services;

import com.prospera.corebanking.dto.models.entities.Nasabah;

import com.prospera.corebanking.dto.models.repos.NasabahRepo;
import com.prospera.corebanking.dto.request.NasabahData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NasabahService {
    @Autowired
    private NasabahRepo nasabahRepo;

    public Nasabah saveNasabah (NasabahData nasabahData){

        // Buat handler jika number nya ke generate yang sama

        System.out.println(nasabahData);

        Nasabah nasabah = new Nasabah();


        nasabah.setNama(nasabahData.getNama());
        nasabah.setNikKtp(nasabahData.getNikKtp());
        nasabah.setEmail(nasabahData.getEmail());
        nasabah.setPassword(nasabahData.getPassword());
        nasabah.setNoHP(nasabahData.getNoHP());
        nasabah.setPekerjaan(nasabahData.getPekerjaan());
        nasabah.setAlamat(nasabahData.getAlamat());
        nasabah.setFlagWarungTepat(nasabahData.getFlagWarungTepat());
        nasabah.setTanggalBuat(nasabahData.getTanggalBuat());
        nasabah.setNikKtp(nasabahData.getNikKtp());

        return nasabahRepo.save(nasabah);
    }



    public Nasabah findByNikKtp (Long nikKtp){
        Nasabah nasabah = nasabahRepo.findByNikKtp(nikKtp);
//        if (!supplier.isPresent()){
//            return null;
//        }
        return nasabah;
    }

    public Nasabah update (Nasabah nasabah){
        return nasabahRepo.save(nasabah);
    }

    public Iterable<Nasabah> findAll(){
        return nasabahRepo.findAll();
    }

    public void removeOne(Long id){
        nasabahRepo.deleteById(id);
    }
}
