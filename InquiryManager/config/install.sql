drop table if exists result;
drop table if exists survey;
drop table if exists answer;
drop table if exists question;
drop table if exists inquiry;

create table inquiry
  (
     id        int primary key auto_increment,
     body  varchar(1000) not null
  ) character set utf8 collate utf8_unicode_ci;
  
create table question
  (
    id  int primary key auto_increment,
    body  varchar(1000) not null,
	multivalue boolean not null,
	inquiry_id int not null,
	key fk_question_inquiry_idx (inquiry_id),
  	constraint fk_question_inquiry foreign key (inquiry_id) references inquiry (id)
  ) character set utf8 collate utf8_unicode_ci;
  
create table answer
(
	id int primary key auto_increment,
	body varchar(500) not null,
	valid boolean not null,
	question_id int not null,
	key fk_answer_question_idx (question_id),
	constraint fk_answer_question foreign key (question_id) references question (id)
)	character set utf8 collate utf8_unicode_ci;

  insert into inquiry (body) values ("первый тест");
  insert into inquiry (body) values ("второй опрос");
  insert into question (body,multivalue,inquiry_id) values ("первый вопрос тест1",false,1);
  insert into question (body,multivalue,inquiry_id) values ("второй вопрос тест1",true,1);
  insert into question (body,multivalue,inquiry_id) values ("первый вопрос для втрого опроса",false,2);
  insert into answer (body,valid,question_id) values ("ответ1 на первый вопрос теста 1",false,1);
  insert into answer (body,valid,question_id) values ("ответ2 на первый вопрос теста 1",true,1);
  insert into answer (body,valid,question_id) values ("ответ3 на первый вопрос теста 1",true,1);
  insert into answer (body,valid,question_id) values ("ответ4 на первый вопрос теста 1",false,1);
  insert into answer (body,valid,question_id) values ("ответ1 на первый вопрос теста 2",false,2);
  insert into answer (body,valid,question_id) values ("ответ2 на первый вопрос теста 2",false,2);
  insert into answer (body,valid,question_id) values ("ответ3 на первый вопрос теста 2",false,2);
  insert into answer (body,valid,question_id) values ("ответ4 на первый вопрос теста 2",true,2);
    
create table survey
  (
     id	int primary key auto_increment,
     fio  varchar(100) not null,
     start datetime default null,
     stop datetime default null,
     status varchar(100) default null,
     inquiry_id int not null,
     key fk_survey_inquiry_idx (inquiry_id),
  	constraint fk_survey_inquiry foreign key (inquiry_id) references inquiry (id)

  ) character set utf8 collate utf8_unicode_ci;  

create table result
  (
     id        int primary key auto_increment,
     answer_id int not null,
     survey_id int not null,
     key fk_result_survey_idx (survey_id),
          key fk_result_answer_idx (answer_id),
  		constraint fk_result_survey foreign key (survey_id) references survey (id),
  		constraint fk_result_answer foreign key (answer_id) references answer (id)
  ) character set utf8 collate utf8_unicode_ci;  
