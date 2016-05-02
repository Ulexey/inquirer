package ru.lda.inquirer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.lda.inquirer.dao.ResultDAO;
import ru.lda.inquirer.domain.Result;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultDAO resultDAO;

	@Override
	public void addResult(Result result) {
		resultDAO.addResult(result);

	}

	@Transactional
	@Override
	public List<Result> listResult() {
		return resultDAO.listResult();
	}

	@Transactional
	@Override
	public void removeResult(Long id) {
		resultDAO.removeResult(id);
	}

	@Transactional
	@Override
	public Result findResultById(Long id) {
		return resultDAO.findResultById(id);
	}


	@Override
	public List<Result> findResultsByInquiry(Long inquiryId) {
		return resultDAO.findResultsByInquiry(inquiryId);
	}

	
	@Override
	public void saveResult(Result result) {
		resultDAO.saveResult(result);
	}

	@Override
	public List<Result> findResultsBySurvey(Long id) {
		return resultDAO.findResultsBySurvey(id);
	}

}
