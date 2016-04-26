package ru.lda.inquirer.dao;

import java.util.List;

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
		if(result != null){
			sessionFactory.getCurrentSession().delete(result);
		}
	}
	
	@Override
	public Result findResultById(Long id) {
		return (Result) sessionFactory.getCurrentSession().load(Result.class, id);

	}
}
