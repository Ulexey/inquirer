package ru.lda.inquirer.dao;

import java.util.List;

import ru.lda.inquirer.domain.Inquiry;

public interface InquiryDAO {
	public void addInquiry(Inquiry inquiry);
	public List<Inquiry> listInquiry();
	public void removeInquiry(Long id);
	public Inquiry findInquiryById(Long id);
}
