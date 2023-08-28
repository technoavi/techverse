package com.narensoft.fs.service;

import com.narensoft.fs.DAO.FishRepository;
import com.narensoft.fs.DAO.FishermanRepository;
import com.narensoft.fs.models.Fish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class FishService {

    private FishRepository fishRepository;

    @Autowired
    FishService(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    public List<Fish> getAllFishForFisherman(int fishermanId) {
        return fishRepository.getFishByFisherman(fishermanId);
    }

//    public Fish addFish(int fishermanId, Fish fish) {
//        fis
//        fishRepository.delete();
//
//    }
    /*
    removeFish();
    getAllFishByLocation();
    getAllFishByLocationList();*/
}

