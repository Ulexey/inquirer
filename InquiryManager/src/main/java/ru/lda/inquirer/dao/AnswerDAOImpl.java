package ru.lda.inquirer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.lda.inquirer.domain.Answer;

@Repository
public class AnswerDAOImpl implements AnswerDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addAnswer(Answer answer) {
		sessionFactory.getCurrentSession().save(answer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> listAnswer() {
		return sessionFactory.getCurrentSession().createQuery("from Question").list();
	}

	@Override
	public void removeAnswer(Long id) {
		Answer answer = (Answer) sessionFactory.getCurrentSession().load(Answer.class, id);
		if(answer != null){
			sessionFactory.getCurrentSession().delete(answer);
		}
	}
	
	@Override
	public Answer findAnswerById(Long id) {
		return (Answer) sessionFactory.getCurrentSession().load(Answer.class, id);

	}
	
	@Override
	 public void saveAnswer(Answer answer) {
		  Session session = sessionFactory.getCurrentSession();
		  Answer existingAnswer = (Answer) session.get(Answer.class, answer.getId());
		  existingAnswer.setBody(answer.getBody());
		  existingAnswer.setValid(answer.getValid());
		  existingAnswer.setQuestion(answer.getQuestion());
		  session.save(existingAnswer);
		 }
	
	
	
}
