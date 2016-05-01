package ru.lda.inquirer.dao;

import java.util.List;

import ru.lda.inquirer.domain.Question;
import ru.lda.inquirer.domain.Result;

public interface ResultDAO {
	public void addResult(Result result);
	public List<Result> listResult();
	public void removeResult(Long id);
	public Result findResultById(Long id);
	public List<Result> findResultsByInquiry(Long inquiryId);

}
