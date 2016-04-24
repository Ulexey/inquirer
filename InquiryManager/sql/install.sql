DROP TABLE IF EXISTS QUESTION;
DROP TABLE IF EXISTS INQUIRy ;

CREATE TABLE INQUIRY
  (
     id        INT PRIMARY KEY AUTO_INCREMENT,
     body  VARCHAR(1000)
  ) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
  
CREATE TABLE QUESTION
  (
    id  INT PRIMARY KEY AUTO_INCREMENT,
    body  VARCHAR(1000),
	multivalue BOOLEAN NOT NULL,
	inquiry_id INT NOT NULL,
	KEY fk_question_inquiry_idx (inquiry_id),
  	CONSTRAINT fk_question_inquiry FOREIGN KEY (inquiry_id) REFERENCES inquiry (id)
  ) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
  
  
  insert into inquiry (body) values ("первый тест");
  insert into inquiry (body) values ("второй опрос");
  insert into question (body,multivalue,inquiry_id) values ("первый вопрос",false,1);
  insert into question (body,multivalue,inquiry_id) values ("второй вопрос",true,1);
  insert into question (body,multivalue,inquiry_id) values ("первый вопрос для втрого опроса",false,2);
