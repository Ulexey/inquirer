package ru.lda.inquirer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.lda.inquirer.dao.InquiryDAO;
import ru.lda.inquirer.domain.Inquiry;

@Service

public class InquiryServiceImpl implements InquiryService {

	@Autowired
	private InquiryDAO inquiryDAO;

	@Override
	public void addInquiry(Inquiry inquiry) {
		inquiryDAO.addInquiry(inquiry);

	}

	@Transactional
	@Override
	public List<Inquiry> listInquiry() {
		return inquiryDAO.listInquiry();
	}

	@Transactional
	@Override
	public void removeInquiry(Long id) {
		inquiryDAO.removeInquiry(id);
	}


	@Transactional
	@Override
	public Inquiry findInquiryById(Long inquiryId) {
		return inquiryDAO.findInquiryById(inquiryId);
	}



}
