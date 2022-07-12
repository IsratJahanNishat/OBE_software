package com.dev.FinalProject.service;

import java.util.List;

import com.dev.FinalProject.entity.CO2;

public interface CO2ExcelDataService {
	List<CO2> getExcelDataAsList();
	
	int saveExcelData(List<CO2> invoices1);
}
