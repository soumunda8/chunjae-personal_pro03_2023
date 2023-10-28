CREATE DATABASE teaspoon_personal;

USE teaspoon_personal;

CREATE TABLE board(
	seq INT AUTO_INCREMENT PRIMARY KEY,					-- 게시글 번호 : 자동 증가
	boardNm VARCHAR(100) NOT NULL,						-- 게시판 이름
	author VARCHAR(20) NOT NULL,   						-- 게시판 작성자
	title VARCHAR(100) NOT NULL,							-- 게시글 제목
	content VARCHAR(1000) NOT NULL,						-- 게시글 내용
	regDate DATETIME DEFAULT CURRENT_TIMESTAMP(),	-- 게시글 등록일
	visited INT DEFAULT 0									-- 게시글 조회수
);

CREATE VIEW boardList AS (SELECT b.seq AS seq, b.boardNm AS boardNm, b.author AS author, m.name AS name, b.title AS title, b.content AS content, b.regDate AS regDate, b.visited AS visited FROM board b, member m WHERE b.author = m.id order BY b.seq ASC);

CREATE TABLE fileBoard(
	fno INT PRIMARY KEY AUTO_INCREMENT,   							-- 파일번호: 자동발생
	boardNm VARCHAR(100) NOT NULL,									-- 해당 게시글 이름
	seq INT NOT NULL,   													-- 해당 게시글 번호
	saveFolder VARCHAR(1000) NOT NULL,								-- 파일 저장 폴더
	originNm VARCHAR(500) NOT NULL,									-- 파일 원래 이름
	saveNm VARCHAR(500) NOT NULL,										-- 파일 저장 이름
	uploadDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP	-- 파일 업로드 일자
);

CREATE TABLE commentBoard(
   cno INT PRIMARY KEY AUTO_INCREMENT,   							-- 댓글번호: 자동발생
	boardNm VARCHAR(100) NOT NULL,									-- 해당 게시글 이름
	seq INT NOT NULL,   													-- 해당 게시글 번호
	author VARCHAR(20) NOT NULL,   									-- 댓글 작성자
	content VARCHAR(1000) NOT NULL,   								-- 댓글 내용
	resDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP   	-- 댓글 등록일
);

CREATE VIEW commentList AS (SELECT c.cno AS cno, c.boardNm AS boardNm, c.seq AS boardNo, c.author AS author, m.name AS name, c.content AS content, c.resDate AS resDate FROM commentBoard c, member m WHERE c.author = m.id order BY c.cno ASC);

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