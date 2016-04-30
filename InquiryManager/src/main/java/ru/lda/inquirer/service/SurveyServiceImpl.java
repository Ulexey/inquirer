package ru.lda.inquirer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.lda.inquirer.dao.SurveyDAO;
import ru.lda.inquirer.domain.Survey;

@Service

@Transactional
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyDAO surveyDAO;

	@Override
	public void addSurvey(Survey survey) {
		surveyDAO.addSurvey(survey);

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

	
}
