package com.prospera.corebanking.services;

import com.prospera.corebanking.dto.models.entities.Pembiayaan;
import com.prospera.corebanking.dto.models.entities.Tabungan;
import com.prospera.corebanking.dto.models.repos.PembiayaanRepo;
import com.prospera.corebanking.dto.models.repos.TabunganRepo;
import com.prospera.corebanking.dto.request.PembiayaanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PembiayaanService {
    @Autowired
    private PembiayaanRepo pembiayaanRepo;
    @Autowired
    private TabunganRepo tabunganRepo;

    public Pembiayaan savePembiayaan (PembiayaanData pembiayaanData){

        // Buat handler jika number nya ke generate yang sama

        System.out.println(pembiayaanData);

        Pembiayaan pembiayaan = new Pembiayaan();

        pembiayaan.setStatus(pembiayaanData.getStatus());
        pembiayaan.setJumlahPembiayaan(pembiayaanData.getJumlahPembiayaan());
        pembiayaan.setJumlahHarusBayar(pembiayaanData.getJumlahHarusBayar());
        pembiayaan.setJumlahHarusBayarBulan(pembiayaanData.getJumlahHarusBayarBulan());
        pembiayaan.setTanggalPembiayaan(pembiayaanData.getTanggalPembiayaan());
        pembiayaan.setTenor(pembiayaanData.getTenor());
        //handler tabungan jika udah ada
        //@Query("SELECT p FROM tabungan p WHERE t nikKtp=?1")
        long number = (long) Math.floor(Math.random() * 9_000_000L) + 1_000_000L; //handler kalo dupolicate
        Tabungan tabungan = new Tabungan();
//        tabungan.setNikKtp();
        tabungan.setNoRekening(number);
        tabungan.setSaldo(pembiayaanData.getJumlahPembiayaan());

        System.out.println(tabungan);
        tabunganRepo.save(tabungan);

        return pembiayaanRepo.save(pembiayaan);
    }

    /*public Officer findOne (Long id){
        Optional<Officer> supplier = officerRepo.findById(id);
        if (!supplier.isPresent()){
            return null;
        }
        return supplier.get();
    }*/

    /*public Tabungan findByNoRekening (Long noRekening){
        Tabungan tabungan = tabunganRepo.findByNoRekening(noRekening);
//        if (!supplier.isPresent()){
//            return null;
//        }
        return tabungan;
    }*/

    /*public Pembiayaan update (Pembiayaan pembiayaan){
        return pembiayaanRepo.save(pembiayaan);
    }*/

    public Iterable<Pembiayaan> findAll(){
        return pembiayaanRepo.findAll();
    }

    /*public void removeOne(Long id){
        pembiayaanRepo.deleteById(id);
    }*/
}
