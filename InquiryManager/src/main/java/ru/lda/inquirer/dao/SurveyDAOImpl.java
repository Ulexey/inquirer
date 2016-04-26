package ru.lda.inquirer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.lda.inquirer.domain.Survey;

@Repository
public class SurveyDAOImpl implements SurveyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addSurvey(Survey survey) {
		sessionFactory.getCurrentSession().save(survey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> listSurvey() {
		return sessionFactory.getCurrentSession().createQuery("from Survey").list();
	}

	@Override
	public void removeSurvey(Long id) {
		Survey survey = (Survey) sessionFactory.getCurrentSession().load(Survey.class, id);
		if (survey != null) {
			sessionFactory.getCurrentSession().delete(survey);
		}
	}

	@Override
	public Survey findSurveyById(Long id) {
		return (Survey) sessionFactory.getCurrentSession().load(Survey.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> findSurveysByFIO(String fio) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Survey where fio=:fio");
		query.setParameter("fio", fio);
		return query.list();
	}
}
