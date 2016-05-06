package ru.lda.inquirer.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.lda.inquirer.dao.ResultDAOImpl;
import ru.lda.inquirer.dao.SurveyDAO;
import ru.lda.inquirer.domain.Result;
import ru.lda.inquirer.domain.Survey;

@Service

@Transactional
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyDAO surveyDAO;
	
	@Resource
	ResultDAOImpl resultRepository; 


	@Override
	public void addSurvey(Survey survey) {

		surveyDAO.addSurvey(survey);
		for (Result result : survey.getResults()) {
			resultRepository.addResult(result);
		}
	}

	@Override
	public List<Survey> listSurvey() {
		return surveyDAO.listSurvey();
	}

	@Override
	public void removeSurvey(Long id) {
		surveyDAO.removeSurvey(id);
	}

	@Override
	public Survey findSurveyById(Long id) {
		return surveyDAO.findSurveyById(id);
	}
	
	@Override
	public List<Survey> findSurveysByFIO(String fio){
		return surveyDAO.findSurveysByFIO(fio);
	}

	@Override
	public void saveSurvey(Survey survey) {
		surveyDAO.saveSurvey(survey);
	}

	@Override
	public List<Result> findResultsBySurvey(Long id) {
		return surveyDAO.findResultsBySurvey(id);
	}
	
	
}
