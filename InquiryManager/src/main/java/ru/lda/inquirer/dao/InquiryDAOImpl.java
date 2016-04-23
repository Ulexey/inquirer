package ru.lda.inquirer.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.lda.inquirer.domain.Inquiry;

@Repository
public class InquiryDAOImpl implements InquiryDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addInquiry(Inquiry inquiry) {
		sessionFactory.getCurrentSession().save(inquiry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inquiry> listInquiry() {
		return sessionFactory.getCurrentSession().createQuery("from Inquiry").list();
	}

	@Override
	public void removeInquiry(Long id) {
		Inquiry inquiry= (Inquiry) sessionFactory.getCurrentSession().load(Inquiry.class, id);
		if(inquiry != null){
			sessionFactory.getCurrentSession().delete(inquiry);
		}
	}
	
	@Override
	public Inquiry findInquiryById(Long id) {
		return (Inquiry) sessionFactory.getCurrentSession().load(Inquiry.class, id);

	}
}
