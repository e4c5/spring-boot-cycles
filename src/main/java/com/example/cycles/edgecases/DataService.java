package com.example.cycles.edgecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DataService: Part of cycle with QualifiedService.
 * 
 * Tests @Qualifier annotation preservation.
 */
@Service("primaryDataService")
public class DataService {

    @Autowired
    private QualifiedService qualifiedService;

    public String fetchData(String key) {
        qualifiedService.processData(key);
        return "data-" + key;
    }
}

