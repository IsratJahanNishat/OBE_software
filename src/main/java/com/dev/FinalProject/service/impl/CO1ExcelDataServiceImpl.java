package com.dev.FinalProject.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dev.FinalProject.entity.CO1;
import com.dev.FinalProject.repo.CO1Repository;
import com.dev.FinalProject.service.CO1ExcelDataService;


@Service
public class CO1ExcelDataServiceImpl implements CO1ExcelDataService{

	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;

	@Autowired
	CO1Repository repo1;
	
	Workbook workbook1;

	public List<CO1> getExcelDataAsList() {

		List<String> list = new ArrayList<String>();

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// Create the Workbook
		try {
			workbook1 = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// Retrieving the number of sheets in the Workbook
		System.out.println("-------Workbook has '" + workbook1.getNumberOfSheets() + "' Sheets-----");

		// Getting the Sheet at index zero
		Sheet sheet1 = workbook1.getSheetAt(0);

		// Getting number of columns in the Sheet
		int noOfColumns = sheet1.getRow(0).getLastCellNum();
		System.out.println("-------Sheet has '"+noOfColumns+"' columns------");

		// Using for-each loop to iterate over the rows and columns
		for (Row row : sheet1) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}

		// filling excel data and creating list as List<Invoice>
		List<CO1> invList = createList(list, noOfColumns);

		// Closing the workbook
		try {
			workbook1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invList;
	}

	private List<CO1> createList(List<String> excelData, int noOfColumns) {

		ArrayList<CO1> invList1 = new ArrayList<CO1>();

		int i = noOfColumns;
		do {
			CO1 inv = new CO1();

			inv.setStudentid(excelData.get(i));
			inv.setStudentname(excelData.get(i + 1));
			inv.setQuiz(Double.valueOf(excelData.get(i + 2)));
			inv.setClassperf(Double.valueOf(excelData.get(i + 3)));
			inv.setFinalexam(Double.valueOf(excelData.get(i + 4)));
			inv.setTotal(Double.valueOf(excelData.get(i + 5)));
			
			

			invList1.add(inv);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		return invList1;
	}

	@Override
	public int saveExcelData(List<CO1> invoices1) {
		invoices1 = repo1.saveAll(invoices1);
		return invoices1.size();
	}
}
