package ru.lda.inquirer.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.lda.inquirer.domain.Question;

@Repository
public class QuestionDAOImpl implements QuestionDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addQuestion(Question question) {
		sessionFactory.getCurrentSession().save(question);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> listQuestion() {
		return sessionFactory.getCurrentSession().createQuery("from Question").list();
	}

	@Override
	public void removeQuestion(Long id) {
		Question question= (Question) sessionFactory.getCurrentSession().load(Question.class, id);
		if(question != null){
			sessionFactory.getCurrentSession().delete(question);
		}
	}
	
	@Override
	public Question findQuestionById(Long id) {
		return (Question) sessionFactory.getCurrentSession().load(Question.class, id);

	}
}
