DROP TABLE IF EXISTS INQUIRY_QUESTION;
DROP TABLE IF EXISTS QUESTION;
DROP TABLE IF EXISTS INQUIRy ;

CREATE TABLE INQUIRY
  (
     inquiry_id        INT PRIMARY KEY AUTO_INCREMENT,
     body  VARCHAR(500)
  ) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
  
CREATE TABLE QUESTION
  (
    question_id  INT PRIMARY KEY AUTO_INCREMENT,
    body  VARCHAR(500),
	multivalue BOOLEAN 
  ) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
  
  CREATE TABLE INQUIRY_QUESTION
  (
	inquiry_id INT NOT NULL,
	question_id INT NOT NULL,
	PRIMARY KEY (inquiry_id,question_id),
	UNIQUE KEY question_id_unique (question_id),
	KEY fk_inquiry (inquiry_id),
	KEY fk_question (question_id),
	CONSTRAINT fk_inquiry FOREIGN KEY (inquiry_id) REFERENCES inquiry (inquiry_id),
	CONSTRAINT fk_question FOREIGN KEY (question_id) REFERENCES question (question_id)
  ) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
  
  insert into inquiry (body) values ("первый тест");
  insert into inquiry (body) values ("второй опрос");
  insert into question (body,multivalue) values ("первый вопрос",false);
  insert into question (body,multivalue) values ("второй вопрос",true);
  insert into question (body,multivalue) values ("первый вопрос для втрого опроса",false);
  insert into inquiry_question (inquiry_id,question_id) values (1,1);
  insert into inquiry_question (inquiry_id,question_id) values (1,2);
  insert into inquiry_question (inquiry_id,question_id) values (2,3);
  