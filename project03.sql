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