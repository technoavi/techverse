package com.techverse.oneToOneuniDir.service;

import com.techverse.oneToOneuniDir.DAO.FishermanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishermanService {
    @Autowired
    private FishermanRepository fishermanRepository;

}
