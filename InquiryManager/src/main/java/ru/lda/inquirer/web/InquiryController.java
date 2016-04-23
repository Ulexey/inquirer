package ru.lda.inquirer.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.lda.inquirer.domain.Inquiry;
import ru.lda.inquirer.service.InquiryService;

@Controller
public class InquiryController {

	@Autowired
	private InquiryService inquiryService;
	
	@RequestMapping("/index")
	public String listInquires(Map<String,Object> map){
		map.put("inquiry",new Inquiry());
		map.put("inquiryList",inquiryService.listInquiry());
		return "inquiry";
	}
	
	@RequestMapping("/")
	public String home(){
		return "redirect:/index"; 
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addInquiry(@ModelAttribute("inquiry") Inquiry inquiry, BindingResult result){
		inquiryService.addInquiry(inquiry);
		return "redirect:/index";
	}
	
	@RequestMapping("delete/inquiry/{inquiryId}")
	public String deleteInquiry(@PathVariable("inquiryId") Long inquiryId){
		inquiryService.removeInquiry(inquiryId);
		return "redirect:/index";
	}

	
	@RequestMapping("/fill/{inquiryId}")
	public String fillInquiry(@PathVariable("inquiryId") Long inquiryId, ModelMap map ){
		Inquiry inquiry = inquiryService.findInquiryById(inquiryId);
		map.addAttribute("inquiry",inquiry);
		return "fill";
	}

}
