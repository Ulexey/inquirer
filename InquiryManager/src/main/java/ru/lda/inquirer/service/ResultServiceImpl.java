package ru.lda.inquirer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.lda.inquirer.dao.ResultDAO;
import ru.lda.inquirer.domain.Result;

@Service

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

}
