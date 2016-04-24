package ru.lda.inquirer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.lda.inquirer.dao.QuestionDAO;
import ru.lda.inquirer.domain.Question;

@Service

public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;

	@Override
	public void addQuestion(Question question) {
		questionDAO.addQuestion(question);

	}

	@Transactional
	@Override
	public List<Question> listQuestion() {
		return questionDAO.listQuestion();
	}

	@Transactional
	@Override
	public void removeQuestion(Long id) {
		questionDAO.removeQuestion(id);
	}


	@Transactional
	@Override
	public Question findQuestionById(Long id) {
		return questionDAO.findQuestionById(id);
	}



}
