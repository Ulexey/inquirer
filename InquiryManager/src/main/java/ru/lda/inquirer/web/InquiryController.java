package ru.lda.inquirer.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ru.lda.inquirer.domain.Answer;
import ru.lda.inquirer.domain.Inquiry;
import ru.lda.inquirer.domain.Question;
import ru.lda.inquirer.domain.Result;
import ru.lda.inquirer.domain.ResultForm;
import ru.lda.inquirer.domain.Survey;
import ru.lda.inquirer.service.AnswerService;
import ru.lda.inquirer.service.InquiryService;
import ru.lda.inquirer.service.QuestionService;
import ru.lda.inquirer.service.ResultService;
import ru.lda.inquirer.service.SurveyService;

@Controller
public class InquiryController {

	@Autowired
	private InquiryService inquiryService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private SurveyService surveyService;

	@Resource
	private ResultService resultService;
	
	private Logger logger = Logger.getLogger(InquiryController.class);
	
	private void methodNameToLog(){
		logger.info("start "+Thread.currentThread().getStackTrace()[2].getMethodName());
	}
	
	private void messageToLog(String message){
		logger.info(message);
	}

	// опросы
	
	@RequestMapping("/inquiries/fill")
	public String fillInquires(ModelMap map) throws Exception {
		methodNameToLog();
		map.put("inquiry", new Inquiry());
		map.put("inquiries", inquiryService.listInquiry());
		return "inquiries";
	}

	@RequestMapping("/")
	public String home() {
		methodNameToLog();
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		methodNameToLog();
		return "redirect:/inquiries/fill";
	}

	@RequestMapping(value = "/inquiry/add", method = RequestMethod.POST)
	public String addInquiry(@ModelAttribute("inquiry") Inquiry inquiry)  throws Exception{
		methodNameToLog();
		inquiryService.addInquiry(inquiry);
		messageToLog("inquiry "+inquiry.getId()+" added");
		return "redirect:/index";
	}

	@RequestMapping("/inquiry/{inquiryId}/delete")
	public String deleteInquiry(@PathVariable("inquiryId") Long inquiryId) throws Exception {
		methodNameToLog();
		inquiryService.removeInquiry(inquiryId);
		messageToLog("inquiry "+inquiryId+" deleted");
		return "redirect:/index";
	}


	// Вопросы
	
	@RequestMapping("/inquiry/{inquiryId}/questions/fill")
	public String fillQuestions(@PathVariable("inquiryId") Long inquiryId, ModelMap map)  throws Exception{
		methodNameToLog();
		map.put("inquiry", inquiryService.findInquiryById(inquiryId));
		map.put("question", new Question());
		return "questions";
	}
	

	@RequestMapping("/inquiry/{inquiryId}/question/{questionId}/delete")
	public String deleteQuestion(@PathVariable("questionId") Long questionId, @PathVariable("inquiryId") Long inquiryId) throws Exception {
		methodNameToLog();
		questionService.removeQuestion(questionId);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/questions/fill";
		messageToLog("question "+questionId+" deleted");
		return redirectUrl;
	}

	@RequestMapping(value = "/inquiry/{inquiryId}/question/add", method = RequestMethod.POST)
	public String addQuestion(@PathVariable("inquiryId") Long inquiryId, @ModelAttribute("question") Question question,
			BindingResult result) throws Exception {
		methodNameToLog();
		question.setInquiry(inquiryService.findInquiryById(inquiryId));
		questionService.addQuestion(question);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/questions/fill";
		messageToLog("question "+question.getId()+" added");
		return redirectUrl;
	}

	
	// Ответы
	
	@RequestMapping("/inquiry/{inquiryId}/question/{questionId}/answers/fill")
	public String fillAnswers(@PathVariable("questionId") Long questionId, @PathVariable("inquiryId") Long inquiryId,
			ModelMap map) throws Exception {
		methodNameToLog();
		map.put("question", questionService.findQuestionById(questionId));
		map.put("inquiry", inquiryService.findInquiryById(inquiryId));
		map.put("answer", new Answer());
		return "answers";
	}

	
	@RequestMapping(value = "/inquiry/{inquiryId}/question/{questionId}/answer/add", method = RequestMethod.POST)
	public String addAnswer(@PathVariable("inquiryId") Long inquiryId, @PathVariable("questionId") Long questionId,
			@ModelAttribute("answer") Answer answer, BindingResult result)  throws Exception{
		methodNameToLog();
		answer.setQuestion(questionService.findQuestionById(questionId));
		answerService.addAnswer(answer);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/question/" + questionId+"/answers/fill";
		messageToLog("answer "+answer.getId()+" added");
		return redirectUrl;
	}

	@RequestMapping("/inquiry/{inquiryId}/question/{questionId}/answer/{answerId}/delete")
	public String deleteAnswer(@PathVariable("inquiryId") Long inquiryId, @PathVariable("questionId") Long questionId,
			@PathVariable("answerId") Long answerId) throws Exception {
		methodNameToLog();
		answerService.removeAnswer(answerId);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/question/" + questionId+"/answers/fill";
		messageToLog("answer "+answerId+" deleted");
		return redirectUrl;
	}

	@RequestMapping(value = "/inquiry/{inquiryId}/question/{questionId}/answer/{answerId}/edit", method = RequestMethod.GET)
	public String editAnswer(@PathVariable("inquiryId") Long inquiryId, @PathVariable("questionId") Long questionId,
			@PathVariable("answerId") Long answerId, ModelMap map) throws Exception {
		methodNameToLog();
		map.put("answer", answerService.findAnswerById(answerId));
		map.put("question", questionService.findQuestionById(questionId));
		map.put("inquiry", inquiryService.findInquiryById(inquiryId));
		return "answer/edit";
	}

	@RequestMapping(value = "/inquiry/{inquiryId}/question/{questionId}/answer/{answerId}/edit", method = RequestMethod.POST)
	public String editAnswerPost(@PathVariable("inquiryId") Long inquiryId, @PathVariable("questionId") Long questionId,
			@PathVariable("answerId") Long answerId, @ModelAttribute("answer") Answer answer) throws Exception {
		methodNameToLog();
		answer.setId(answerId);
		answer.setQuestion(questionService.findQuestionById(questionId));
		answerService.saveAnswer(answer);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/question/" + questionId+"/answers/fill";
		messageToLog("answer "+answer.getId()+" saved");

		return redirectUrl;
	}

	// Прохождения

	@RequestMapping(value = "/inquiry/{inquiryId}/surveys/fill", method = RequestMethod.GET)
	public String fillSurveys(@RequestParam("fio") String fio, @PathVariable("inquiryId") Long inquiryId,
			ModelMap map) throws Exception {
		methodNameToLog();
		map.put("inquiry", inquiryService.findInquiryById(inquiryId));
		Survey survey = new Survey();
		map.put("survey", survey);
		List<Survey> surveyList = surveyService.findSurveysByFIO(fio);
		map.put("surveyList", surveyList);
		return "surveys";
	}

	@RequestMapping(value = "/inquiry/{inquiryId}/survey/add", params = "add", method = RequestMethod.POST)
	public String addSurveyPost(@PathVariable("inquiryId") Long inquiryId, @ModelAttribute("survey") Survey survey,@ModelAttribute("resultForm") ResultForm resultForm,
			BindingResult result, Model model) throws Exception {
		methodNameToLog();
		Inquiry inqury = inquiryService.findInquiryById(inquiryId);
		survey.setInquiry(inqury);

		List<Question> questions = inqury.getQuestions();
		List<Result> results = new ArrayList<Result>();
		for (Question question : questions) {
			for (Answer answer : question.getAnswers()) {
				Result newResult = new Result();
				newResult.setSurvey(survey);
				newResult.setAnswer(answer);
				newResult.setQuestion(question);
				results.add(newResult);
			}
		}
		survey.setResults(results);
		surveyService.addSurvey(survey);
		model.addAttribute("survey", survey);
		messageToLog("survey "+survey.getId()+" added");
		return "survey/showBeforeProcessMaking";

	}

	@RequestMapping(value = "/inquiry/{inquiryId}/survey/add", params = "show", method = RequestMethod.POST)
	public String addSurveyShow(@PathVariable("inquiryId") Long inquiryId, @ModelAttribute("survey") Survey survey,
			BindingResult result, ModelMap map) throws Exception {
		methodNameToLog();
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/surveys/fill?fio="
				+ survey.getFio().trim().replace(" ", "%20");
		return redirectUrl;
	}
	
	@RequestMapping(value = "/inquiry/{inquiryId}/survey/{surveyId}/start", method = RequestMethod.GET)
	public String startSurvey(@PathVariable("inquiryId") Long inquiryId, @PathVariable("surveyId") Long surveyId, 
			ModelMap model)  throws Exception{
		methodNameToLog();
		Inquiry inquiry = inquiryService.findInquiryById(inquiryId);
		ResultForm resultForm = new ResultForm();
		
		List<Question> temp = inquiry.getQuestions();
		for (Question question : temp) {
			for (Iterator<Result> iterator = question.getResults().iterator(); iterator.hasNext();) {
				Result result = (Result) iterator.next();
				if (result.getSurvey().getId().longValue()!=surveyId) iterator.remove();
			}
		}
		//TODO проставить время начала прохождения  и статус проставить start
		resultForm.setQuestions(temp);
		model.addAttribute("resultForm",resultForm);
		messageToLog("survey "+surveyId+" started");
		return "survey/showProcessMaking";
	}
	
	@RequestMapping(value = "/inquiry/{inquiryId}/survey/{surveyId}/finish", method = RequestMethod.POST)
	public String finishSurvey(@PathVariable("surveyId") Long surveyId,@ModelAttribute("resultForm") ResultForm resultForm, 
			ModelMap model) throws Exception {
		methodNameToLog();
		for (Question question : resultForm.getQuestions()) {
			for (Result result : question.getResults()) {
				resultService.saveResult(result);
			}
		}
		Survey survey = surveyService.findSurveyById(surveyId);
		model.put("survey",survey);
		//TODO проставить время окончания прохождения и статус обновить до finish
		messageToLog("survey "+survey.getId()+" finished");

		return "survey/showAfterProcessMaking";
	}
	
	@RequestMapping(value = "/inquiry/{inquiryId}/survey/{surveyId}/show", method = RequestMethod.GET)
	public String showSurvey(@PathVariable("surveyId") Long surveyId,
			ModelMap map) throws Exception{
		methodNameToLog();
		Survey survey = surveyService.findSurveyById(surveyId);
		map.put("results",survey.getResults());
		return "result/list";
	}
	
	@RequestMapping(value = "/surveys/list")
	public String listSurveys(ModelMap map) throws Exception{
		methodNameToLog();
		List<Survey> surveys = surveyService.listSurvey();
		map.put("surveys", surveys);
		return "survey/list";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e)  throws Exception{
		logger.info(e.getMessage());
		ModelAndView modelAndView=new ModelAndView("error");
		modelAndView.addObject("error", e.getMessage());
		return modelAndView;
	}
	
	@ExceptionHandler()
	public ModelAndView handleException() throws Exception {
		String text="Exception in site";
		logger.info(text);
		ModelAndView modelAndView=new ModelAndView("error");
		modelAndView.addObject("error", text);
		return modelAndView;
	}

}
