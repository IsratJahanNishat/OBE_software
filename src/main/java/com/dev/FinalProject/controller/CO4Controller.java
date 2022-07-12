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

import com.dev.FinalProject.entity.CO4;
import com.dev.FinalProject.repo.CO4Repository;
import com.dev.FinalProject.service.CO4ExcelDataService;
import com.dev.FinalProject.service.CO4FileUploaderService;

@Controller
public class CO4Controller {

	double avg4=0.0;

	@Autowired
	CO4FileUploaderService fileService1;
	
	@Autowired
	CO4ExcelDataService excelservice1;
	
	@Autowired
	CO4Repository repo1;
	
	
	
	@GetMapping("/addCO4")
    public String index() {
        return "CO4uploadPage";
    }

    @PostMapping("/CO4uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {

        fileService1.uploadFile(file);

        redirectAttributes.addFlashAttribute("f4message", "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        //model.put("message", "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/addCO4";
    }
    
    @GetMapping("/CO4saveData")
    public String saveExcelData(RedirectAttributes redirectAttributes) {
    	
    	List<CO4> excelDataAsList = excelservice1.getExcelDataAsList();
    	int noOfRecords = excelservice1.saveExcelData(excelDataAsList);
    	redirectAttributes.addFlashAttribute("d4message", "Data Saved");
    	return "redirect:/addCO4";
    }
    
    @GetMapping("/calculateCO4")
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
    	avg4=total1/newlist.size();
    	redirectAttributes.addFlashAttribute("co4message", avg4);
    	System.out.println("size:"+newlist.size());
    	System.out.println("average:"+avg4);
    	
    	return "redirect:/addCO4";
    	
    }
    double giveavg4() {
    	return avg4;
    }
}
