package ru.lda.inquirer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.lda.inquirer.domain.Answer;
import ru.lda.inquirer.domain.Inquiry;
import ru.lda.inquirer.domain.Question;
import ru.lda.inquirer.service.AnswerService;
import ru.lda.inquirer.service.InquiryService;
import ru.lda.inquirer.service.QuestionService;

@Controller
public class InquiryController {

	@Autowired
	private InquiryService inquiryService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	// опросы
	@RequestMapping("/index")
	public String listInquires(ModelMap map) {
		map.put("inquiry", new Inquiry());
		map.put("inquiryList", inquiryService.listInquiry());
		return "index";
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping(value = "/addInquiry", method = RequestMethod.POST)
	public String addInquiry(@ModelAttribute("inquiry") Inquiry inquiry, BindingResult result) {
		inquiryService.addInquiry(inquiry);
		return "redirect:/index";
	}

	@RequestMapping("/inquiry/delete/{inquiryId}")
	public String deleteInquiry(@PathVariable("inquiryId") Long inquiryId) {
		inquiryService.removeInquiry(inquiryId);
		return "redirect:/index";
	}

	@RequestMapping("/inquiry/{inquiryId}")
	public String fillInquiry(@PathVariable("inquiryId") Long inquiryId, ModelMap map) {
		map.put("inquiry", inquiryService.findInquiryById(inquiryId));
		map.put("question", new Question());
		return "inquiry";
	}

	// Вопросы
	@RequestMapping("/inquiry/{inquiryId}/question/{questionId}")
	public String fillQuestion(@PathVariable("questionId") Long questionId, @PathVariable("inquiryId") Long inquiryId,
			ModelMap map) {
		map.put("question", questionService.findQuestionById(questionId));
		map.put("inquiry", inquiryService.findInquiryById(inquiryId));
		map.put("answer", new Answer());
		return "question";
	}

	@RequestMapping("/question/delete/{questionId}")
	public String deleteQuestion(@RequestParam("inquiryId") Long inquiryId,
			@PathVariable("questionId") Long questionId) {
		questionService.removeQuestion(questionId);
		String redirectUrl = "redirect:/inquiry/" + inquiryId;
		return redirectUrl;
	}

	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public String addQuestion(@RequestParam("inquiryId") Long inquiryId, @ModelAttribute("question") Question question,
			BindingResult result) {
		question.setInquiry(inquiryService.findInquiryById(inquiryId));
		questionService.addQuestion(question);
		String redirectUrl = "redirect:/inquiry/" + inquiryId;
		return redirectUrl;
	}

	// Ответы

	@RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
	public String addAnswer(@RequestParam("questionId") Long questionId, @RequestParam("inquiryId") Long inquiryId,
			@ModelAttribute("answer") Answer answer, BindingResult result) {
		answer.setQuestion(questionService.findQuestionById(questionId));
		answerService.addAnswer(answer);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/question/" + questionId;
		return redirectUrl;
	}

	@RequestMapping("/inquiry/{inquiryId}/question/{questionId}/answer/{answerId}/delete")
	public String deleteAnswer(@PathVariable("inquiryId") Long inquiryId, @PathVariable("questionId") Long questionId, @PathVariable("answerId") Long answerId) {
		answerService.removeAnswer(answerId);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/question/" + questionId;
		return redirectUrl;
	}

	@RequestMapping("/inquiry/{inquiryId}/question/{questionId}/answer/{answerId}")
	public String fillAnswer(@PathVariable("answerId") Long answerId, ModelMap map) {
		map.put("answer", answerService.findAnswerById(answerId));
		return "answer";
	}

	@RequestMapping(value = "/inquiry/{inquiryId}/question/{questionId}/answer/{answerId}/edit", method = RequestMethod.POST)
	public String postEdit(@PathVariable("inquiryId") Long inquiryId,@PathVariable("questionId") Long questionId,@PathVariable("answerId") Long answerId,@ModelAttribute("answer") Answer answer) {
		answer.setId(answerId);
		answer.setQuestion(questionService.findQuestionById(questionId));
		answerService.saveAnswer(answer);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/question/" + questionId;
		return redirectUrl;
	}

}
