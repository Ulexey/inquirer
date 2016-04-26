package ru.lda.inquirer.dao;

import java.util.List;

import ru.lda.inquirer.domain.Question;
import ru.lda.inquirer.domain.Survey;

public interface SurveyDAO {
	public void addSurvey(Survey survey);
	public List<Survey> listSurvey();
	public void removeSurvey(Long id);
	public Survey findSurveyById(Long id);
}
