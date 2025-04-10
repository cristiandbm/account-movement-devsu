package com.devsu.service;

import java.time.LocalDate;
import java.util.List;

import com.devsu.dto.Report;

public interface IReportService {

	List<Report> getReport(String clientId, LocalDate starDate,
			LocalDate endDate);

}
