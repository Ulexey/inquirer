package ru.lda.inquirer.service;

import ru.lda.inquirer.dao.QuestionDAO;
import ru.lda.inquirer.domain.Question;

public interface QuestionService extends QuestionDAO{


	Question findQuestionById(Long id);

}
