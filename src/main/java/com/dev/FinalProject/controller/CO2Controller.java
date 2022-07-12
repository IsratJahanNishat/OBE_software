package com.dev.FinalProject.controller;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev.FinalProject.entity.CO2;
import com.dev.FinalProject.repo.CO2Repository;
import com.dev.FinalProject.service.CO2ExcelDataService;
import com.dev.FinalProject.service.CO2FileUploaderService;

@Controller
public class CO2Controller {

	double avg2=0.0;

	@Autowired
	CO2FileUploaderService fileService1;
	
	@Autowired
	CO2ExcelDataService excelservice1;
	
	@Autowired
	CO2Repository repo1;
	
	
	
	@GetMapping("/addCO2")
    public String index() {
        return "CO2uploadPage";
    }

    @PostMapping("/CO2uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {

        fileService1.uploadFile(file);
        redirectAttributes.addFlashAttribute("f2message", "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/addCO2";
    }
    
    @GetMapping("/CO2saveData")
    public String saveExcelData(RedirectAttributes redirectAttributes) {
    	
    	List<CO2> excelDataAsList = excelservice1.getExcelDataAsList();
    	int noOfRecords = excelservice1.saveExcelData(excelDataAsList);
    	redirectAttributes.addFlashAttribute("d2message", "Data Saved");
    	return "redirect:/addCO2";
    }
    
    
    @GetMapping("/calculateCO2")
    public String calculate(RedirectAttributes redirectAttributes){
    	List<Integer> newlist=repo1.getByTable();
    	ListIterator<Integer> itr3=newlist.listIterator();
    	for(int i = 0; i < newlist.size(); i++) {  
    		if(newlist.get(i)>=50) {
    			newlist.set(i, 3);
    		}else if(newlist.get(i)<50 && newlist.get(i)>30){
    			newlist.set(i,2);
    		}else {
    			newlist.set(i, 1);
    		}
    	}
    	
    	while(itr3.hasNext()) {
    		
    		System.out.println(itr3.nextIndex()+":"+itr3.next());
    	}
    	double total1=0;
    	for(int i = 0; i < newlist.size(); i++) {  
    		total1=total1+newlist.get(i);
    	}	
    	avg2=total1/newlist.size();
    	redirectAttributes.addFlashAttribute("co2message", avg2);
    	System.out.println("size:"+newlist.size());
    	System.out.println("average:"+avg2);
    	
    	return "redirect:/addCO2";
    	
    }
    double giveavg2() {
    	return avg2;
    }
}

