package com.example.cycles.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Setter injection cycle: DataService depends on ReportService via setter.
 */
@Service
public class DataService {

    private ReportService reportService;

    @Autowired
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public String fetchData(String dataType) {
        System.out.println("Fetching data: " + dataType);
        return "Data for " + dataType;
    }

    public void refreshData(String reportId) {
        String status = reportService.getReportStatus(reportId);
        System.out.println("Report " + reportId + " status: " + status);
    }
}
