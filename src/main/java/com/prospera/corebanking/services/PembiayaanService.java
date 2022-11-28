package com.prospera.corebanking.services;

import com.prospera.corebanking.dto.models.entities.*;
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
public class PembiayaanService {
    @Autowired
    private PembiayaanRepo pembiayaanRepo;
    @Autowired
    private TabunganRepo tabunganRepo;

    @Autowired
    private NasabahRepo nasabahRepo;

    @Autowired
    private TabunganHistoryRepo tabunganHistoryRepo;

    @Autowired
    private TabunganHistoryService tabunganHistoryService;

    public Pembiayaan savePembiayaan (PembiayaanData pembiayaanData){

        Nasabah nasabah = nasabahRepo.findByNikKtp(pembiayaanData.getNikKtp());
        Tabungan existingTabungan = tabunganRepo.findByNikKtp(pembiayaanData.getNikKtp());

//        System.out.println(nasabah.getAlamat());

        

        // Buat handler jika number nya ke generate yang sama

        System.out.println(pembiayaanData);

        Pembiayaan pembiayaan = new Pembiayaan();

        pembiayaan.setNikKtp(pembiayaanData.getNikKtp());
        pembiayaan.setNama(nasabah.getNama());

        long nomorPembiayaan = (long) Math.floor(Math.random() * 9_000_000L) + 1_000_000L;
        Pembiayaan existNoPembiayaan = pembiayaanRepo.findByNoPembiayaan(nomorPembiayaan);
        while(existNoPembiayaan != null){
            // membuat kembali no rekening
            nomorPembiayaan = (long) Math.floor(Math.random() * 9_000_000L) + 1_000_000L;
            Pembiayaan cekNoPembiayaan = pembiayaanRepo.findByNoPembiayaan(nomorPembiayaan);
            if(cekNoPembiayaan != null){
                // cek kembali no rekening
                nomorPembiayaan = (long) Math.floor(Math.random() * 9_000_000L) + 1_000_000L;
                break;}
        }

        pembiayaan.setNoPembiayaan(nomorPembiayaan);
        pembiayaan.setStatus(pembiayaanData.getStatus());
        pembiayaan.setJumlahPembiayaan(pembiayaanData.getJumlahPembiayaan());
        pembiayaan.setJumlahHarusBayar(pembiayaanData.getJumlahHarusBayar());
        pembiayaan.setJumlahHarusBayarBulan(pembiayaanData.getJumlahHarusBayarBulan());
        pembiayaan.setTanggalPembiayaan(new Date());
        pembiayaan.setTenor(pembiayaanData.getTenor());
        //handler tabungan jika udah ada
        long number = (long) Math.floor(Math.random() * 9_000_000L) + 1_000_000L; //handler kalo dupolicate
        Tabungan existingRekening = tabunganRepo.findByNoRekening(number);
        if (existingTabungan == null){
            System.out.println("membuat rekening baru");
            while(existingRekening != null){
                number = (long) Math.floor(Math.random() * 9_000_000L) + 1_000_000L;
                Tabungan cekexistingRekening = tabunganRepo.findByNoRekening(number);
                if(cekexistingRekening != null){
                    number = (long) Math.floor(Math.random() * 9_000_000L) + 1_000_000L;
                    break;}
            }
            Tabungan tabungan = new Tabungan();
            tabungan.setNikKtp(pembiayaanData.getNikKtp());
            tabungan.setNama(nasabah.getNama());
            tabungan.setNoRekening(number);
            tabungan.setSaldo(pembiayaanData.getJumlahPembiayaan());

            // Buat History Tabungan

            tabunganHistoryService.saveTransaksi(number, "pembiayaan", pembiayaanData.getJumlahPembiayaan());
            System.out.println(tabungan);
            tabunganRepo.save(tabungan);
        }

        if(existingTabungan != null) {
            System.out.println("tambah saldo ke rekening yang udah ada");
//            Tabungan existingTabungan = tabunganRepo.findByNikKtp(nasabah.getNikKtp());
            existingTabungan.setSaldo(existingTabungan.getSaldo() + pembiayaanData.getJumlahPembiayaan());
            tabunganHistoryService.saveTransaksi(existingTabungan.getNoRekening(), "pembiayaan", pembiayaanData.getJumlahPembiayaan());
            tabunganRepo.save(existingTabungan);
        }

//        Tabungan adaTabungan = tabunganRepo.findByNoRekening()



        return pembiayaanRepo.save(pembiayaan);
    }

    public Pembiayaan findOne (Long id){
        Pembiayaan pembiayaan = pembiayaanRepo.findByNoPembiayaan(id);
//        if (!pembiayaan.isPresent()){
//            return null;
//        }
        return pembiayaan;
    }

    /*public Tabungan findByNoRekening (Long noRekening){
        Tabungan tabungan = tabunganRepo.findByNoRekening(noRekening);
//        if (!supplier.isPresent()){
//            return null;
//        }
        return tabungan;
    }*/

    public Pembiayaan update (Pembiayaan pembiayaan) {
        return pembiayaanRepo.save(pembiayaan);
    }

    public Iterable<Pembiayaan> findAll(){
        return pembiayaanRepo.findAll();
    }

    /*public void removeOne(Long id){
        pembiayaanRepo.deleteById(id);
    }*/
}
