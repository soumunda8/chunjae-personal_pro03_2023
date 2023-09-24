CREATE DATABASE teaspoon_personal;

USE teaspoon_personal;

CREATE TABLE notice(
	seq INT AUTO_INCREMENT PRIMARY KEY,					-- 공지 번호 : 자동 증가
	title VARCHAR(100) NOT NULL,							-- 공지 제목
	content VARCHAR(1000) NOT NULL,						-- 공지 내용
	regDate DATETIME DEFAULT CURRENT_TIMESTAMP(),	-- 공지 등록일
	visited INT DEFAULT 0									-- 공지 조회수
);

CREATE TABLE MEMBER(
	id VARCHAR(20) PRIMARY KEY,							-- 회원 아이디
	pw VARCHAR(300) NOT NULL,								-- 회원 비밀번호
	name VARCHAR(50) NOT NULL,								-- 회원 이름
	email VARCHAR(50) NOT NULL,							-- 회원 이메일
	tel VARCHAR(20) NOT NULL,								-- 회원 전화번호
	addr1 VARCHAR(200),										-- 회원 주소
	addr2 VARCHAR(100),										-- 회원 상세 주소
	postcode VARCHAR(10),									-- 회원 우편 번호
	regDate DATETIME DEFAULT CURRENT_TIMESTAMP(),	-- 회원 등록일
	birth DATE,													-- 회원 생일
	pt INT(11) DEFAULT 0,									-- 회원 포인트
	visited INT(11) DEFAULT 0,								-- 회원 방문수
	useYn BOOLEAN DEFAULT TRUE,							-- 회원 사용 여부
	grade INT DEFAULT 2										-- 회원 등급 [ 0 : 관리자, 1 : 미정, 2 : 일반사용자]
);

CREATE TABLE vote(
	vno INT AUTO_INCREMENT PRIMARY KEY,								-- 투표번호 : 자동증가
	title VARCHAR(200) NOT NULL,										-- 투표제목
	content VARCHAR(1000) NOT NULL,									-- 투표내용
	regDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,		-- 투표등록일
	visited INT DEFAULT 0,												-- 조회수
	startDate DATE,														-- 투표 시작일
	finishDate DATE,														-- 투표 종료일
	useYn BOOLEAN DEFAULT FALSE,										-- 투표 사용여부 - 사용자 화면에 띄울지 말지 선택
	stateYn BOOLEAN DEFAULT TRUE,										-- 투표 상태여부 - 종료, 진행 상태
	answer INT,																-- 투표 결과 lno
	addPt INT NOT NULL 													-- 완료후 등록될 포인트
);

CREATE TABLE voteAnswerList(
	lno INT AUTO_INCREMENT PRIMARY KEY,								-- 질문지 내역 번호 : 자동증가
	vno INT NOT NULL,														-- 질문지 해당 투표번호
	title VARCHAR(200) NOT NULL,										-- 질문지 제목
	colorNum VARCHAR(10) NOT NULL                            -- 투표율 색상지정
);

CREATE TABLE voteAnswerUser(
	uno INT AUTO_INCREMENT PRIMARY KEY,								-- 질문자 번호 : 자동증가
	vno INT NOT NULL,														-- 질문지 해당 투표번호
	lno INT NOT NULL,														-- 질문지 해당 번호 
	author VARCHAR(20) NOT NULL										-- 질문자 아이디
);

CREATE VIEW voteCount AS (SELECT l.lno AS lno, l.vno AS vno, l.title AS title, l.colorNum AS colorNum, COUNT(u.uno) AS cnt FROM voteanswerlist l LEFT JOIN voteansweruser u ON l.lno = u.lno GROUP BY l.lno);

CREATE TABLE attendance (
	seq INT AUTO_INCREMENT PRIMARY KEY,								-- 출석 번호 : 자동증가
	author VARCHAR(20) NOT NULL,										-- 출석 해당 멤버
	dateYearMonth VARCHAR(6) NOT NULL,								-- 해당 년월
	nowDay VARCHAR(2) NOT NULL											-- 출석 날짜
);