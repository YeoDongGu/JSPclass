CREATE TABLE MEMBER
(
	id varchar2(10) NOT NULL,
	pass varchar2(10) NOT NULL,
	name varchar2(30) NOT NULL,
	regidate DATE DEFAULT sysdate NOT NULL,
	PRIMARY key(id)
)

CREATE TABLE board
(
	num NUMBER PRIMARY key,
	title varchar2(200) NOT NULL,
	content varchar2(2000) NOT NULL,
	id varchar2(10) NOT NULL,
	postdate DATE DEFAULT sysdate NOT NULL,
	visitcount number(6)
)

ALTER TABLE BOARD 
	ADD CONSTRAINT board_mem_fk FOREIGN KEY (id)
	REFERENCES MEMBER (id)
	
CREATE SEQUENCE seq_board_num
INCREMENT BY 1
START WITH 1
MINVALUE 1
nomaxvalue
nocycle
nocache

DROP TABLE member

INSERT INTO MEMBER (id,pass,name) VALUES ('nanovia3', 'ehdrn1', '여동구')

INSERT INTO board(num, title, content, id, postdate, visitcount)
VALUES (seq_board_num.nextval, '제목1입니다', '내용1입니다', 'musthave', sysdate, 0)

INSERT INTO MEMBER (id,pass,name) VALUES ('musthave2', '1231', '머스트해브2')

INSERT INTO board(num, title, content, id, postdate, visitcount)
VALUES (seq_board_num.nextval, '제목2입니다', '내용2입니다', 'musthave2', sysdate, 0)

INSERT INTO board VALUES (seq_board_num.nextval, '지금은 봄입니다', '봄의왈츠', 'musthave', sysdate, 0)

INSERT INTO board VALUES (seq_board_num.nextval, '지금은 여름입니다', '여름향기', 'musthave', sysdate, 0)

INSERT INTO board VALUES (seq_board_num.nextval, '지금은 가을입니다', '가을동화', 'musthave', sysdate, 0)

INSERT INTO board VALUES (seq_board_num.nextval, '지금은 겨울입니다', '겨울연가', 'musthave', sysdate, 0)

CREATE TABLE myfile (
	idx NUMBER PRIMARY KEY,
	name varchar2(50) NOT NULL,
	title varchar2(200) NOT NULL,
	cate varchar2(30),
	ofile varchar2(100) NOT NULL,
	sfile varchar2(30) NOT NULL,
	postdate DATE DEFAULT sysdate NOT null
)

CREATE TABLE mvcboard
(
	idx NUMBER PRIMARY KEY,
	name varchar2(50) NOT NULL,
	title varchar2(200) NOT NULL,
	content varchar2(2000) NOT NULL,
	postdate DATE DEFAULT sysdate NOT NULL,
	ofile varchar2(200),
	sfile varchar2(30),
	downcount number(5) DEFAULT 0 NOT NULL,
	pass varchar2(50) NOT NULL,
	visitcount NUMBER DEFAULT 0 NOT null
)

INSERT INTO mvcboard (idx, name, title, content, pass)
	VALUES (seq_board_num.nextval, '김유신', '자료실 제목1 입니다.','내용','1234')
	
INSERT INTO mvcboard (idx, name, title, content, pass)
	VALUES (seq_board_num.nextval, '장보고', '자료실 제목2 입니다.','내용','1234')
	
INSERT INTO mvcboard (idx, name, title, content, pass)
	VALUES (seq_board_num.nextval, '이순신', '자료실 제목3 입니다.','내용','1234')
	
INSERT INTO mvcboard (idx, name, title, content, pass)
	VALUES (seq_board_num.nextval, '강감찬', '자료실 제목4 입니다.','내용','1234')
	
INSERT INTO mvcboard (idx, name, title, content, pass)
	VALUES (seq_board_num.nextval, '대조영', '자료실 제목5 입니다.','내용','1234')
	

