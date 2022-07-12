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

import com.dev.FinalProject.entity.CO3;
import com.dev.FinalProject.repo.CO3Repository;
import com.dev.FinalProject.service.CO3ExcelDataService;
import com.dev.FinalProject.service.CO3FileUploaderService;

@Controller
public class CO3Controller {
	double avg3=0.0;

	@Autowired
	CO3FileUploaderService fileService1;
	
	@Autowired
	CO3ExcelDataService excelservice1;
	
	@Autowired
	CO3Repository repo1;
	
	
	
	@GetMapping("/addCO3")
    public String index() {
        return "CO3uploadPage";
    }

    @PostMapping("/CO3uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {

        fileService1.uploadFile(file);

        redirectAttributes.addFlashAttribute("f3message", "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        //model.put("message", "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/addCO3";
    }
    
    @GetMapping("/CO3saveData")
    public String saveExcelData(RedirectAttributes redirectAttributes) {
    	
    	List<CO3> excelDataAsList = excelservice1.getExcelDataAsList();
    	int noOfRecords = excelservice1.saveExcelData(excelDataAsList);
    	redirectAttributes.addFlashAttribute("d3message", "Data Saved");
    	return "redirect:/addCO3";
    }
    
    @GetMapping("/calculateCO3")
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
    	avg3=total1/newlist.size();
    	redirectAttributes.addFlashAttribute("co3message", avg3);
    	System.out.println("size:"+newlist.size());
    	System.out.println("average:"+avg3);
    	
    	return "redirect:/addCO3";
    	
    }
    double giveavg3() {
    	return avg3;
    }

}
