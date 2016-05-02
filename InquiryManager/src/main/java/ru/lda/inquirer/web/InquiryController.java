package ru.lda.inquirer.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String addInquiry(@ModelAttribute("inquiry") Inquiry inquiry) {
		System.out.println("!!!!!! "+inquiry.getId());
		inquiryService.addInquiry(inquiry);
		System.out.println("!!!!!@#! "+inquiry.getId());
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
	public String deleteAnswer(@PathVariable("inquiryId") Long inquiryId, @PathVariable("questionId") Long questionId,
			@PathVariable("answerId") Long answerId) {
		answerService.removeAnswer(answerId);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/question/" + questionId;
		return redirectUrl;
	}

	@RequestMapping("/inquiry/{inquiryId}/question/{questionId}/answer/{answerId}")
	public String fillAnswer(@PathVariable("inquiryId") Long inquiryId, @PathVariable("questionId") Long questionId,
			@PathVariable("answerId") Long answerId, ModelMap map) {
		map.put("answer", answerService.findAnswerById(answerId));
		map.put("question", questionService.findQuestionById(questionId));
		map.put("inquiry", inquiryService.findInquiryById(inquiryId));
		return "answer";
	}

	@RequestMapping(value = "/inquiry/{inquiryId}/question/{questionId}/answer/{answerId}/edit", method = RequestMethod.POST)
	public String postEditAnswer(@PathVariable("inquiryId") Long inquiryId, @PathVariable("questionId") Long questionId,
			@PathVariable("answerId") Long answerId, @ModelAttribute("answer") Answer answer) {
		answer.setId(answerId);
		answer.setQuestion(questionService.findQuestionById(questionId));
		answerService.saveAnswer(answer);
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/question/" + questionId;
		return redirectUrl;
	}

	// Прохождения

	@RequestMapping(value = "/inquiry/{inquiryId}/survey/list", method = RequestMethod.GET)
	public String getAddSurvey(@RequestParam("fio") String fio, @PathVariable("inquiryId") Long inquiryId,
			ModelMap map) {
		map.put("inquiry", inquiryService.findInquiryById(inquiryId));
		Survey survey = new Survey();
		map.put("survey", survey);
		List<Survey> surveyList = surveyService.findSurveysByFIO(fio);
		map.put("surveyList", surveyList);
		return "surveys";
	}

	@RequestMapping(value = "/inquiry/{inquiryId}/survey/add", params = "add", method = RequestMethod.POST)
	public String postAddSurvey(@PathVariable("inquiryId") Long inquiryId, @ModelAttribute("survey") Survey survey,@ModelAttribute("resultForm") ResultForm resultForm,
			BindingResult result, Model model) {
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

		return "result2";

	}

	@RequestMapping(value = "/inquiry/{inquiryId}/survey/add/{surveyId}", method = RequestMethod.GET)
	public String addSurvey(@PathVariable("inquiryId") Long inquiryId, @PathVariable("surveyId") Long surveyId, 
			ModelMap model) {
		Survey survey = surveyService.findSurveyById(surveyId);
		model.put("survey",survey);
		return "result";
	}

	@RequestMapping(value = "/inquiry/{inquiryId}/survey/add", params = "show", method = RequestMethod.POST)
	public String showAddSurvey(@PathVariable("inquiryId") Long inquiryId, @ModelAttribute("survey") Survey survey,
			BindingResult result, ModelMap map) {
		String redirectUrl = "redirect:/inquiry/" + inquiryId + "/survey/list?fio="
				+ survey.getFio().trim().replace(" ", "%20");
		return redirectUrl;
	}
	
	@RequestMapping(value = "/inquiry/{inquiryId}/survey/{surveyId}/start", method = RequestMethod.GET)
	public String startSurvey(@PathVariable("inquiryId") Long inquiryId, @PathVariable("surveyId") Long surveyId, 
			ModelMap model) {
		Inquiry inquiry = inquiryService.findInquiryById(inquiryId);
		ResultForm resultForm = new ResultForm();
		
		List<Question> temp = inquiry.getQuestions();
		for (Question question : temp) {
			for (Iterator<Result> iterator = question.getResults().iterator(); iterator.hasNext();) {
				Result result = (Result) iterator.next();
				if (result.getSurvey().getId().longValue()!=surveyId) iterator.remove();
			}
		}
		
		resultForm.setQuestions(temp);
		model.addAttribute("resultForm",resultForm);
		
		return "editresults";
		
	}
	
	
	@RequestMapping(value = "/inquiry/{inquiryId}/survey/{surveyId}/savve", method = RequestMethod.POST)
	public String savveSurvey(@PathVariable("surveyId") Long surveyId,@ModelAttribute("resultForm") ResultForm resultForm, 
			ModelMap model) {
		
		for (Question question : resultForm.getQuestions()) {
			for (Result result : question.getResults()) {
				resultService.saveResult(result);
			}
		}
		return "editresults";
	}

//	@RequestMapping(value = "/inquiry/{inquiryId}/survey/add/saveSurvey/{surveyId}", method = RequestMethod.POST)
//	public String shoswAddSurvey(@PathVariable("inquiryId") Long inquiryId,@PathVariable("surveyId") Long surveyId,@ModelAttribute("survey") Survey survey) {
//		System.out.println("$$$$$$$$" + survey.getId());
//
//		
//		survey.setId(surveyId);
//		survey.setInquiry(inquiryService.findInquiryById(inquiryId));
//		surveyService.saveSurvey(survey);
//		for (Result result : resultService.findResultsBySurvey(survey.getId())) {
//
//			resultService.saveResult(result);
//		}
//		System.out.println("$$$$$$$$" + survey.getId());
//		
//		String redirectUrl = "redirect:/index";
//
//		return redirectUrl;
//	}

}
