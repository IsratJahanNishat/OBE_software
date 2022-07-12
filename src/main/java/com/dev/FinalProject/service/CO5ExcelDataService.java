package com.dev.FinalProject.service;

import java.util.List;

import com.dev.FinalProject.entity.CO5;

public interface CO5ExcelDataService {

	List<CO5> getExcelDataAsList();
	
	int saveExcelData(List<CO5> invoices1);
}
