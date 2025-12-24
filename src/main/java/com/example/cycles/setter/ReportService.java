package com.example.cycles.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Setter injection cycle: ReportService depends on DataService via setter.
 */
@Service
public class ReportService {

    private DataService dataService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public void generateReport(String reportType) {
        System.out.println("Generating report: " + reportType);
        String data = dataService.fetchData(reportType);
        System.out.println("Report data: " + data);
    }

    public String getReportStatus(String reportId) {
        return "COMPLETED";
    }
}
