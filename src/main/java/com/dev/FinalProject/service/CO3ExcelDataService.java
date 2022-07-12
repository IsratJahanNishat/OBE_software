package com.dev.FinalProject.service;

import java.util.List;

import com.dev.FinalProject.entity.CO3;

public interface CO3ExcelDataService {
	List<CO3> getExcelDataAsList();
	
	int saveExcelData(List<CO3> invoices1);
}
