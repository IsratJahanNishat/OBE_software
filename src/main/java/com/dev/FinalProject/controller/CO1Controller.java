package com.dev.FinalProject.controller;


import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev.FinalProject.entity.CO1;
import com.dev.FinalProject.repo.CO1Repository;
import com.dev.FinalProject.service.CO1ExcelDataService;
import com.dev.FinalProject.service.CO1FileUploaderService;


@Controller
public class CO1Controller {
	
	
	double avg1=0.0;

	@Autowired
	CO1FileUploaderService fileService1;
	
	@Autowired
	CO1ExcelDataService excelservice1;
	
	@Autowired
	CO1Repository repo1;
	
	@GetMapping("/addCO1")
    public String index() {
        return "COuploadPage";
    }

    @PostMapping("/COuploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {

        fileService1.uploadFile(file);
        redirectAttributes.addFlashAttribute("message", "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/addCO1";
    }
    
    @GetMapping("/COsaveData")
    public String saveExcelData(RedirectAttributes redirectAttributes) {
    	
    	List<CO1> excelDataAsList = excelservice1.getExcelDataAsList();
    	int noOfRecords = excelservice1.saveExcelData(excelDataAsList);
    	redirectAttributes.addFlashAttribute("dmessage", "Data Saved");
    	return "redirect:/addCO1";
    }
    
    
    @GetMapping("/calculateCO")
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
    	avg1=total1/newlist.size();
    	redirectAttributes.addFlashAttribute("comessage", avg1);
    	System.out.println("size:"+newlist.size());
    	System.out.println("average:"+avg1);
    	
    	return "redirect:/addCO1";
    	
    }
    double giveavg1() {
    	return avg1;
    }
    
}
