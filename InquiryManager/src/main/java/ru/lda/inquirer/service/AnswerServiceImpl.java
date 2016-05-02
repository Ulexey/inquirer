package ru.lda.inquirer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.lda.inquirer.dao.AnswerDAO;
import ru.lda.inquirer.domain.Answer;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerDAO answerDAO;

	@Override
	public void addAnswer(Answer answer) {
		answerDAO.addAnswer(answer);

	}

	@Transactional
	@Override
	public List<Answer> listAnswer() {
		return answerDAO.listAnswer();
	}

	@Transactional
	@Override
	public void removeAnswer(Long id) {
		answerDAO.removeAnswer(id);
	}


	@Transactional
	@Override
	public Answer findAnswerById(Long id) {
		return answerDAO.findAnswerById(id);
	}
	
	@Transactional
	@Override
	public void saveAnswer(Answer answer) {
		answerDAO.saveAnswer(answer);
	}
	



}
