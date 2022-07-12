package com.dev.FinalProject.service;

import java.util.List;

import com.dev.FinalProject.entity.CO1;


public interface CO1ExcelDataService {
	List<CO1> getExcelDataAsList();
	
	int saveExcelData(List<CO1> invoices1);
}
