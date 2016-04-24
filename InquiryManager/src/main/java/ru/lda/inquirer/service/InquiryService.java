package ru.lda.inquirer.service;

import ru.lda.inquirer.dao.InquiryDAO;
import ru.lda.inquirer.domain.Inquiry;

public interface InquiryService extends InquiryDAO{


	Inquiry findInquiryById(Long id);

}
