package com.devsu.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.dto.Report;
import com.devsu.service.IReportService;

@RestController
@RequestMapping("reportes")
public class ReportController {

	@Autowired
	private IReportService reportService;

	@GetMapping
	public ResponseEntity<List<Report>> getReport(
			@RequestParam(name = "clientId", required = true) String clientId,
			@RequestParam(name = "startDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate startDate,
			@RequestParam(name = "endDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate endDate)
			throws NotFoundException {

		List<Report> report = reportService.getReport(clientId, startDate,
				endDate);
		if (report.isEmpty()) {
			ResponseEntity.noContent().build();
		}
		return new ResponseEntity<List<Report>>(report, HttpStatus.OK);
	}
}
