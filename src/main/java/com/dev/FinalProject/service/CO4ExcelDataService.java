package com.dev.FinalProject.service;

import java.util.List;

import com.dev.FinalProject.entity.CO4;

public interface CO4ExcelDataService {

	List<CO4> getExcelDataAsList();
	
	int saveExcelData(List<CO4> invoices1);
}
