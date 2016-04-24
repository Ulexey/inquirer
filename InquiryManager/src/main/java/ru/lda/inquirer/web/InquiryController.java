package ru.lda.inquirer.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.lda.inquirer.domain.Inquiry;
import ru.lda.inquirer.domain.Question;
import ru.lda.inquirer.service.InquiryService;
import ru.lda.inquirer.service.QuestionService;

@Controller
public class InquiryController {

	@Autowired
	private InquiryService inquiryService;

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/index")
	public String listInquires(ModelMap map){
		map.put("inquiry",new Inquiry());
		map.put("inquiryList",inquiryService.listInquiry());
		return "inquiry";
	}
	
	@RequestMapping("/")
	public String home(){
		return "redirect:/index"; 
	}
	
	@RequestMapping(value="/addInquiry",method=RequestMethod.POST)
	public String addInquiry(@ModelAttribute("inquiry") Inquiry inquiry, BindingResult result){
		inquiryService.addInquiry(inquiry);
		return "redirect:/index";
	}

	@RequestMapping(value="/fill/addQuestion",method=RequestMethod.POST)
	public String addQuestion(@ModelAttribute("question") Question question, BindingResult result){
		questionService.addQuestion(question);
		return "redirect:/index";
	}

	
	@RequestMapping("/delete/inquiry/{inquiryId}")
	public String deleteInquiry(@PathVariable("inquiryId") Long inquiryId){
		inquiryService.removeInquiry(inquiryId);
		return "redirect:/index";
	}

	
	@RequestMapping("/fill/{inquiryId}")
	public String fillInquiry(@PathVariable("inquiryId") Long inquiryId, ModelMap map ){
		map.put("inquiry",inquiryService.findInquiryById(inquiryId));
		map.put("question",new Question());
		return "fill";
	}

}
