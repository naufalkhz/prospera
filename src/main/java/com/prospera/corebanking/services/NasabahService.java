package com.prospera.corebanking.services;

import com.prospera.corebanking.dto.models.entities.Nasabah;

import com.prospera.corebanking.dto.models.entities.Pembiayaan;
import com.prospera.corebanking.dto.models.entities.Tabungan;
import com.prospera.corebanking.dto.models.repos.NasabahRepo;
import com.prospera.corebanking.dto.models.repos.PembiayaanRepo;
import com.prospera.corebanking.dto.models.repos.TabunganRepo;
import com.prospera.corebanking.dto.request.NasabahData;
import com.prospera.corebanking.dto.response.NasabahDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NasabahService {
    @Autowired
    private NasabahRepo nasabahRepo;

    @Autowired
    private TabunganRepo tabunganRepo;

    @Autowired
    private PembiayaanRepo pembiayaanRepo;

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



    public NasabahDTO findByNikKtp (Long nikKtp){
        Nasabah nasabah = nasabahRepo.findByNikKtp(nikKtp);
        Tabungan tabungan = tabunganRepo.findByNikKtp(nikKtp);
        Iterable<Pembiayaan> listPembiayaan = pembiayaanRepo.findAllByNikKtp(nikKtp);

        System.out.println(nasabah);
        System.out.println(listPembiayaan);

        NasabahDTO nasabahDTO = new NasabahDTO();

        nasabahDTO.setSaldo(tabungan.getSaldo());
        nasabahDTO.setNoRekening(tabungan.getNoRekening());

        nasabahDTO.setPembiayaan(listPembiayaan);

        nasabahDTO.setNama(nasabah.getNama());
        nasabahDTO.setNikKtp(nasabah.getNikKtp());
        nasabahDTO.setEmail(nasabah.getEmail());
        nasabahDTO.setPekerjaan(nasabah.getPekerjaan());
        nasabahDTO.setPassword(nasabah.getPassword());
        nasabahDTO.setNoHP(nasabah.getNoHP());
        nasabahDTO.setAlamat(nasabah.getAlamat());
        nasabahDTO.setFlagWarungTepat(nasabah.getFlagWarungTepat());
        nasabahDTO.setTanggalBuat(nasabah.getTanggalBuat());



//        if (!supplier.isPresent()){
//            return null;
//        }
        return nasabahDTO;
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
