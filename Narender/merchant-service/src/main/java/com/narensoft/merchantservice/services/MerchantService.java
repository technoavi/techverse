package com.narensoft.merchantservice.services;

import com.narensoft.merchantservice.DAO.MerchantRepository;
import com.narensoft.merchantservice.models.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public Merchant createMerchant(Merchant merchant) {
        if (Optional.ofNullable(merchant).isPresent() &&
                Optional.ofNullable(merchant.getName()).isPresent() &&
                Optional.ofNullable(merchant.getPhone()).isPresent() &&
                Optional.ofNullable(merchant.getLocation()).isPresent()) {
            return merchantRepository.save(merchant);
        }
        return new Merchant();
    }

    public List<Merchant> getAllMerchant() {
        return merchantRepository.findAll();
    }

    /**
     * 1.How can we use findById(id).orElse()? How can we remove our own if condition?
     */
    public Merchant getMerchantById(Integer id) {
        if (merchantRepository.findById(id).isPresent()) {
            return merchantRepository.findById(id).get();
        }
        return new Merchant();
    }

    /**
     * 1. How to update Merchant name or address or phone?
     */
    public Merchant updateMerchant(Integer id, Merchant updatedMerchant) {
        if (Optional.ofNullable(updatedMerchant).isPresent() && merchantRepository.findById(id).isPresent()) {
            Merchant merchant = merchantRepository.findById(id).get();
            merchant.setName(updatedMerchant.getName());
            merchant.setPhone(updatedMerchant.getPhone());
            merchant.setLocation(updatedMerchant.getLocation());
            merchant.setAddress(updatedMerchant.getAddress());
            merchantRepository.save(merchant);
        }
        return new Merchant();
    }

    public String deleteMerchant(Integer id) {
        if (merchantRepository.findById(id).isPresent()) {
            merchantRepository.deleteById(id);
            return "Merchant deleted";
        }
        return "Merchant doesn't exist";
    }
}
