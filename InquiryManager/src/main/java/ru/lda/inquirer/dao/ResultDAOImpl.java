package ru.lda.inquirer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.lda.inquirer.domain.Result;

@Repository

public class ResultDAOImpl implements ResultDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void addResult(Result result) {
		sessionFactory.getCurrentSession().save(result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> listResult() {
		return sessionFactory.getCurrentSession().createQuery("from Result").list();
	}

	@Override
	public void removeResult(Long id) {
		Result result= (Result) sessionFactory.getCurrentSession().load(Result.class, id);
			sessionFactory.getCurrentSession().delete(result);
	}
	
	@Override
	public Result findResultById(Long id) {
		return (Result) sessionFactory.getCurrentSession().load(Result.class, id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> findResultsByInquiry(Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Result where inquiry=:id");
		query.setParameter("id", id);
		return query.list();
	}


	@Override
	public void saveResult(Result result) {
		  Session session = sessionFactory.getCurrentSession();
		  Result existingResult = (Result) session.get(Result.class, result.getId());
		  existingResult.setAnswer(result.getAnswer());
		  existingResult.setSurvey(result.getSurvey());
		  existingResult.setChecked(result.isChecked());
		  session.save(existingResult);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Result> findResultsBySurvey(Long id) {
		Query query =  sessionFactory.getCurrentSession().createQuery("from Result where id=:id");
		query.setParameter("id", id);
		return query.list();
	}
	
	
}
