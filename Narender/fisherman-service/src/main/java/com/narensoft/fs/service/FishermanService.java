package com.narensoft.fs.service;

import com.narensoft.fs.DAO.FishermanRepository;
import com.narensoft.fs.models.Fish;
import com.narensoft.fs.models.Fisherman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FishermanService {
    private final FishermanRepository fishermanRepository;

    @Autowired
    FishermanService(FishermanRepository fishermanRepository){
        this.fishermanRepository = fishermanRepository;
    }

    public Fisherman createFisherMan(Fisherman fisherman) {
        fisherman = fishermanRepository.save(fisherman);
        return fisherman;
    }

    public Fisherman getFisherManById(int fishermanId) {
        Fisherman fisherman = fishermanRepository.getReferenceById(fishermanId);
        return fisherman;
    }

    public List<Fisherman> getAllFisherMan(){
        return fishermanRepository.findAll();
    }

    public List<Fisherman> getFisherManByLocation(String location) {
        return fishermanRepository.findByLocation(location);
    }

    public List<Fisherman> getFisherManByLocationList(List<String> locations) {
        return fishermanRepository.findByLocationIn(locations);
    }

    updateFisherMan(int id, Fisherman fisherMan);

    getAllFishByFisherManId(int fisherManId);

    addFishToFisherMan(int fisherManId, Fish fish);

    deleteFishForFisherMan(int fisherManId, int fishId);

    deleteFisherManById(int id);
}
