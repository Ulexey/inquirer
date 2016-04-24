package ru.lda.inquirer.dao;

import java.util.List;

import ru.lda.inquirer.domain.Question;

public interface QuestionDAO {
	public void addQuestion(Question question);
	public List<Question> listQuestion();
	public void removeQuestion(Long id);
	public Question findQuestionById(Long id);
}
