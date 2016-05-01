package ru.lda.inquirer.service;

import java.util.List;

import ru.lda.inquirer.dao.SurveyDAO;
import ru.lda.inquirer.domain.Result;
import ru.lda.inquirer.domain.Survey;

public interface SurveyService extends SurveyDAO{
	Survey findSurveyById(Long id);
	List<Survey> findSurveysByFIO(String fio);
	void saveSurvey(Survey survey);
}
