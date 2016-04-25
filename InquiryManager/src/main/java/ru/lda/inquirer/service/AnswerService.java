package ru.lda.inquirer.service;

import ru.lda.inquirer.dao.AnswerDAO;
import ru.lda.inquirer.domain.Answer;

public interface AnswerService extends AnswerDAO{


	Answer findAnswerById(Long id);

}
