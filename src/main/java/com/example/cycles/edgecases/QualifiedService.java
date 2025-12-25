package com.example.cycles.edgecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * QualifiedService: Tests handling of @Qualifier annotations.
 * 
 * This scenario tests if strategies correctly preserve @Qualifier annotations
 * when applying fixes (especially important for InterfaceExtractionStrategy).
 */
@Service
public class QualifiedService {

    @Autowired
    @Qualifier("primaryDataService")
    private DataService dataService;  // Has @Qualifier - must be preserved

    public void processData(String data) {
        String result = dataService.fetchData(data);
        System.out.println("Processed: " + result);
    }
}

