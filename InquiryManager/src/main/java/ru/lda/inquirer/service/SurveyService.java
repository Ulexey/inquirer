package ru.lda.inquirer.service;

import ru.lda.inquirer.dao.SurveyDAO;
import ru.lda.inquirer.domain.Survey;

public interface SurveyService extends SurveyDAO{
	Survey findSurveyById(Long id);
}
