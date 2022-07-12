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

import com.dev.FinalProject.entity.CO5;
import com.dev.FinalProject.repo.CO5Repository;
import com.dev.FinalProject.service.CO5ExcelDataService;
import com.dev.FinalProject.service.CO5FileUploaderService;

@Controller
public class CO5Controller {

	double avg5=0.0;

	@Autowired
	CO5FileUploaderService fileService1;
	
	@Autowired
	CO5ExcelDataService excelservice1;
	
	@Autowired
	CO5Repository repo1;
	
	
	
	@GetMapping("/addCO5")
    public String index() {
		
        return "CO5uploadPage";
    }

    @PostMapping("/CO5uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {

        fileService1.uploadFile(file);
        redirectAttributes.addFlashAttribute("f5message", "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/addCO5";
    }
    
    @GetMapping("/CO5saveData")
    public String saveExcelData(Model model,RedirectAttributes redirectAttributes) {
    	
    	List<CO5> excelDataAsList = excelservice1.getExcelDataAsList();
    	int noOfRecords = excelservice1.saveExcelData(excelDataAsList);
    	model.addAttribute("noOfRecords",noOfRecords);
    	redirectAttributes.addFlashAttribute("d5message", " Data Saved ");
    	return "redirect:/addCO5";
    }
    
    @GetMapping("/calculateCO5")
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
    	avg5=total1/newlist.size();
    	System.out.println("size:"+newlist.size());
    	System.out.println("average:"+avg5);
    	redirectAttributes.addFlashAttribute("co5message",avg5);
    	return "redirect:/addCO5";
    	
    }
    double giveavg5() {
    	return avg5;
    }
}
