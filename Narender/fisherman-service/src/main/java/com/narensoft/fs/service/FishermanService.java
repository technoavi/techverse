package com.narensoft.fs.service;

import com.narensoft.fs.DAO.FishermanRepository;
import com.narensoft.fs.models.Fish;
import com.narensoft.fs.models.Fisherman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.Set;

@Service
public class FishermanService {
    private final FishermanRepository fishermanRepository;

    @Autowired
    FishermanService(FishermanRepository fishermanRepository) {
        this.fishermanRepository = fishermanRepository;
    }

    public Fisherman createFisherman(Fisherman fisherman) {
        return  fishermanRepository.save(fisherman);
    }

    public Fisherman getFishermanById(int fishermanId) {
        Fisherman fisherman = fishermanRepository.findById(fishermanId).get();
        return fisherman;
    }

    public List<Fisherman> getAllFisherman() {
        return fishermanRepository.findAll();
    }

    public List<Fisherman> getFishermanByLocation(String location) {
        return fishermanRepository.findByLocation(location);
    }

    public List<Fisherman> getFishermanByLocationList(List<String> locations) {
        return fishermanRepository.findByLocationIn(locations);
    }

    public Fisherman updateFisherMan(int id, Fisherman fisherman) {

        Fisherman existingFisherman = getFishermanById(id);
        Optional<Fisherman> fishermanOptional = Optional.ofNullable(fisherman);

        if (fishermanOptional.isPresent()) {
            fishermanOptional.map(Fisherman::getName).ifPresent(existingFisherman::setName);
            fishermanOptional.map(Fisherman::getPhone).ifPresent(existingFisherman::setPhone);
            fishermanOptional.map(Fisherman::getLocation).ifPresent(existingFisherman::setLocation);
            fishermanOptional.map(Fisherman::getAddress).ifPresent(existingFisherman::setAddress);
        }

        return fishermanRepository.save(existingFisherman);
    }

    public void deleteFisherman(int id) {
        fishermanRepository.deleteById(id);
    }
}