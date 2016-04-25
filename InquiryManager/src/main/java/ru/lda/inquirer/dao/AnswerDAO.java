package ru.lda.inquirer.dao;

import java.util.List;

import ru.lda.inquirer.domain.Answer;

public interface AnswerDAO {
	public void addAnswer(Answer answer);
	public List<Answer> listAnswer();
	public void removeAnswer(Long id);
	public Answer findAnswerById(Long id);
	public void saveAnswer(Answer answer);
}
