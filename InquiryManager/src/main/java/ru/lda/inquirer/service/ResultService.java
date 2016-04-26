package ru.lda.inquirer.service;

import ru.lda.inquirer.dao.ResultDAO;
import ru.lda.inquirer.domain.Result;

public interface ResultService extends ResultDAO{
	Result findResultById(Long id);
}
