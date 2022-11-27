package com.prospera.corebanking.services;

import com.prospera.corebanking.dto.models.entities.Nasabah;
import com.prospera.corebanking.dto.models.entities.Pembiayaan;
import com.prospera.corebanking.dto.models.entities.Tabungan;
import com.prospera.corebanking.dto.models.entities.TabunganHistory;
import com.prospera.corebanking.dto.models.repos.NasabahRepo;
import com.prospera.corebanking.dto.models.repos.PembiayaanRepo;
import com.prospera.corebanking.dto.models.repos.TabunganHistoryRepo;
import com.prospera.corebanking.dto.models.repos.TabunganRepo;
import com.prospera.corebanking.dto.request.PembiayaanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class TabunganHistoryService {
    @Autowired
    private TabunganHistoryRepo tabunganHistoryRepo;

    public TabunganHistory saveTransaksi (long noRekening, String keterangan, long mutasi){

        TabunganHistory tabunganHistory = new TabunganHistory();

        tabunganHistory.setKeterangan(keterangan);
        tabunganHistory.setNoRekening(noRekening);
        tabunganHistory.setTanggalTransaksi(new Date());
        tabunganHistory.setMutasi(mutasi);

//        tabunganHistoryRepo.save(tabunganHistory);

        return tabunganHistoryRepo.save(tabunganHistory);
    }

//    public Pembiayaan findOne (Long id){
//        Optional<Pembiayaan> pembiayaan = pembiayaanRepo.findById(id);
//        if (!pembiayaan.isPresent()){
//            return null;
//        }
//        return pembiayaan.get();
//    }
//
//    /*public Tabungan findByNoRekening (Long noRekening){
//        Tabungan tabungan = tabunganRepo.findByNoRekening(noRekening);
////        if (!supplier.isPresent()){
////            return null;
////        }
//        return tabungan;
//    }*/
//
//    public Pembiayaan update (Pembiayaan pembiayaan) {
//        return pembiayaanRepo.save(pembiayaan);
//    }
//
    public Iterable<TabunganHistory> findHistoryRekening(long noRekening){
        return tabunganHistoryRepo.findAllByNoRekening(noRekening);
    }
//
//    /*public void removeOne(Long id){
//        pembiayaanRepo.deleteById(id);
//    }*/
}
