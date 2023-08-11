-- 트레이너
DROP TABLE IF EXISTS gym_trainer RESTRICT;

-- 헬스장회원
DROP TABLE IF EXISTS gym_gymmember RESTRICT;

-- 관리자
DROP TABLE IF EXISTS gym_manager RESTRICT;

-- 회원
DROP TABLE IF EXISTS gym_member RESTRICT;

-- GX회원목록
DROP TABLE IF EXISTS gym_gxmemberlist RESTRICT;

-- PT신청
DROP TABLE IF EXISTS gym_ptapp RESTRICT;

-- 게시판
DROP TABLE IF EXISTS gym_board RESTRICT;

-- GX프로그램
DROP TABLE IF EXISTS gym_gxprogram RESTRICT;

-- GX신청
DROP TABLE IF EXISTS gym_gxapp RESTRICT;

-- 새 테이블
DROP TABLE IF EXISTS gym_employ_type RESTRICT;

-- 트레이너
CREATE TABLE gym_trainer (
  member_no INTEGER     NOT NULL COMMENT '트레이너번호', -- 트레이너번호
  school    VARCHAR(60) NOT NULL COMMENT '최종학교', -- 최종학교
  major     VARCHAR(60) NULL     COMMENT '전공', -- 전공
  gtno      INTEGER     NULL     COMMENT '고용형태변호', -- 고용형태변호
  hr_pay    INTEGER     NULL     COMMENT '시강료' -- 시강료
)
COMMENT '트레이너';

-- 트레이너
ALTER TABLE gym_trainer
  ADD CONSTRAINT PK_gym_trainer -- 트레이너 기본키
  PRIMARY KEY (
  member_no -- 트레이너번호
  );

-- 헬스장회원
CREATE TABLE gym_gymmember (
  member_no    INTEGER      NOT NULL COMMENT '헬스장회원번호', -- 헬스장회원번호
  regimonth    DATE         NOT NULL COMMENT '등록기간', -- 등록기간
  created_date DATE         NOT NULL COMMENT '등록일', -- 등록일
  bas_addr     VARCHAR(255) NOT NULL COMMENT '주소번호', -- 주소번호
  det_addr     VARCHAR(255) NULL     COMMENT '상세주소' -- 상세주소
)
COMMENT '헬스장회원';

-- 헬스장회원
ALTER TABLE gym_gymmember
  ADD CONSTRAINT PK_gym_gymmember -- 헬스장회원 기본키
  PRIMARY KEY (
  member_no -- 헬스장회원번호
  );

-- 관리자
CREATE TABLE gym_manager (
  member_no INTEGER     NOT NULL COMMENT '관리자번호', -- 관리자번호
  posi      VARCHAR(60) NULL     COMMENT '직위' -- 직위
)
COMMENT '관리자';

-- 관리자
ALTER TABLE gym_manager
  ADD CONSTRAINT PK_gym_manager -- 관리자 기본키
  PRIMARY KEY (
  member_no -- 관리자번호
  );

-- 회원
CREATE TABLE gym_member (
  member_no    INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  name         VARCHAR(60) NOT NULL COMMENT '이름', -- 이름
  phone_number VARCHAR(30) NOT NULL COMMENT '핸드폰번호', -- 핸드폰번호
  age          VARCHAR(10) NOT NULL COMMENT '나이', -- 나이
  password     INTEGER     NOT NULL COMMENT '비밀번호' -- 비밀번호
)
COMMENT '회원';

-- 회원
ALTER TABLE gym_member
  ADD CONSTRAINT PK_gym_member -- 회원 기본키
  PRIMARY KEY (
  member_no -- 회원번호
  );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_gym_member
  ON gym_member ( -- 회원
    phone_number ASC -- 핸드폰번호
  );

-- 회원 인덱스
CREATE INDEX IX_gym_member
  ON gym_member( -- 회원
    name ASC,     -- 이름
    age ASC,      -- 나이
    password ASC  -- 비밀번호
  );

ALTER TABLE gym_member
  MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- GX회원목록
CREATE TABLE gym_gxmemberlist (
  member_no INTEGER NOT NULL COMMENT '헬스장회원번호', -- 헬스장회원번호
  gx_no     INTEGER NOT NULL COMMENT 'GX번호' -- GX번호
)
COMMENT 'GX회원목록';

-- GX회원목록
ALTER TABLE gym_gxmemberlist
  ADD CONSTRAINT PK_gym_gxmemberlist -- GX회원목록 기본키
  PRIMARY KEY (
  member_no, -- 헬스장회원번호
  gx_no      -- GX번호
  );

-- PT신청
CREATE TABLE gym_ptapp (
  pt_no        INTEGER      NOT NULL COMMENT 'PT신청번호', -- PT신청번호
  trainer_no   INTEGER      NOT NULL COMMENT '트레이너번호', -- 트레이너번호
  member_no    INTEGER      NOT NULL COMMENT '헬스장회원번호', -- 헬스장회원번호
  created_date DATE         NOT NULL COMMENT '신청일', -- 신청일
  state        VARCHAR(10)  NOT NULL COMMENT '상태', -- 상태
  note         VARCHAR(255) NULL     COMMENT '비고' -- 비고
)
COMMENT 'PT신청';

-- PT신청
ALTER TABLE gym_ptapp
  ADD CONSTRAINT PK_gym_ptapp -- PT신청 기본키
  PRIMARY KEY (
  pt_no -- PT신청번호
  );

ALTER TABLE gym_ptapp
  MODIFY COLUMN pt_no INTEGER NOT NULL AUTO_INCREMENT COMMENT 'PT신청번호';

-- 게시판
CREATE TABLE gym_board (
  board_no     INTEGER      NOT NULL COMMENT '게시판번호', -- 게시판번호
  member_no    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title        VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content      MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  password     INTEGER      NOT NULL COMMENT '비밀번호', -- 비밀번호
  view_count   INTEGER      NOT NULL COMMENT '조회수', -- 조회수
  created_date DATE         NOT NULL COMMENT '등록일', -- 등록일
  category     INTEGER      NOT NULL COMMENT '카테고리' -- 카테고리
)
COMMENT '게시판';

-- 게시판
ALTER TABLE gym_board
  ADD CONSTRAINT PK_gym_board -- 게시판 기본키
  PRIMARY KEY (
  board_no -- 게시판번호
  );

ALTER TABLE gym_board
  MODIFY COLUMN board_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시판번호';

-- GX프로그램
CREATE TABLE gym_gxprogram (
  gx_no      INTEGER      NOT NULL COMMENT 'GX번호', -- GX번호
  member_no  INTEGER      NOT NULL COMMENT '트레이너번호', -- 트레이너번호
  title      VARCHAR(255) NOT NULL COMMENT '강의명', -- 강의명
  capacity   INTEGER      NOT NULL COMMENT '개설인원', -- 개설인원
  start_date DATE         NOT NULL COMMENT '시작일', -- 시작일
  end_date   DATE         NOT NULL COMMENT '종료일', -- 종료일
  start_time DATETIME     NULL     COMMENT '시작시간', -- 시작시간
  end_time   DATETIME     NULL     COMMENT '종료시간' -- 종료시간
)
COMMENT 'GX프로그램';

-- GX프로그램
ALTER TABLE gym_gxprogram
  ADD CONSTRAINT PK_gym_gxprogram -- GX프로그램 기본키
  PRIMARY KEY (
  gx_no -- GX번호
  );

-- GX프로그램 인덱스
CREATE INDEX IX_gym_gxprogram
  ON gym_gxprogram( -- GX프로그램
    title ASC -- 강의명
  );

ALTER TABLE gym_gxprogram
  MODIFY COLUMN gx_no INTEGER NOT NULL AUTO_INCREMENT COMMENT 'GX번호';

-- GX신청
CREATE TABLE gym_gxapp (
  gxapp_no     INTEGER      NOT NULL COMMENT '신청번호', -- 신청번호
  member_no    INTEGER      NOT NULL COMMENT '헬스장회원번호', -- 헬스장회원번호
  gx_no        INTEGER      NOT NULL COMMENT 'GX번호', -- GX번호
  created_date DATE         NOT NULL COMMENT '신청일', -- 신청일
  state        VARCHAR(10)  NOT NULL COMMENT '상태', -- 상태
  note         VARCHAR(255) NULL     COMMENT '비고' -- 비고
)
COMMENT 'GX신청';

-- GX신청
ALTER TABLE gym_gxapp
  ADD CONSTRAINT PK_gym_gxapp -- GX신청 기본키
  PRIMARY KEY (
  gxapp_no -- 신청번호
  );

ALTER TABLE gym_gxapp
  MODIFY COLUMN gxapp_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '신청번호';

-- 새 테이블
CREATE TABLE gym_employ_type (
  gtno  INTEGER     NOT NULL COMMENT '고용형태변호', -- 고용형태변호
  title VARCHAR(60) NOT NULL COMMENT '고용형태명' -- 고용형태명
)
COMMENT '새 테이블';

-- 새 테이블
ALTER TABLE gym_employ_type
  ADD CONSTRAINT PK_gym_employ_type -- 새 테이블 기본키
  PRIMARY KEY (
  gtno -- 고용형태변호
  );

ALTER TABLE gym_employ_type
  MODIFY COLUMN gtno INTEGER NOT NULL AUTO_INCREMENT COMMENT '고용형태변호';

-- 트레이너
ALTER TABLE gym_trainer
  ADD CONSTRAINT FK_gym_member_TO_gym_trainer -- 회원 -> 트레이너
  FOREIGN KEY (
  member_no -- 트레이너번호
  )
  REFERENCES gym_member ( -- 회원
  member_no -- 회원번호
  );

-- 트레이너
ALTER TABLE gym_trainer
  ADD CONSTRAINT FK_gym_employ_type_TO_gym_trainer -- 새 테이블 -> 트레이너
  FOREIGN KEY (
  gtno -- 고용형태변호
  )
  REFERENCES gym_employ_type ( -- 새 테이블
  gtno -- 고용형태변호
  );

-- 헬스장회원
ALTER TABLE gym_gymmember
  ADD CONSTRAINT FK_gym_member_TO_gym_gymmember -- 회원 -> 헬스장회원
  FOREIGN KEY (
  member_no -- 헬스장회원번호
  )
  REFERENCES gym_member ( -- 회원
  member_no -- 회원번호
  );

-- 관리자
ALTER TABLE gym_manager
  ADD CONSTRAINT FK_gym_member_TO_gym_manager -- 회원 -> 관리자
  FOREIGN KEY (
  member_no -- 관리자번호
  )
  REFERENCES gym_member ( -- 회원
  member_no -- 회원번호
  );

-- GX회원목록
ALTER TABLE gym_gxmemberlist
  ADD CONSTRAINT FK_gym_gymmember_TO_gym_gxmemberlist -- 헬스장회원 -> GX회원목록
  FOREIGN KEY (
  member_no -- 헬스장회원번호
  )
  REFERENCES gym_gymmember ( -- 헬스장회원
  member_no -- 헬스장회원번호
  );

-- GX회원목록
ALTER TABLE gym_gxmemberlist
  ADD CONSTRAINT FK_gym_gxprogram_TO_gym_gxmemberlist -- GX프로그램 -> GX회원목록
  FOREIGN KEY (
  gx_no -- GX번호
  )
  REFERENCES gym_gxprogram ( -- GX프로그램
  gx_no -- GX번호
  );

-- PT신청
ALTER TABLE gym_ptapp
  ADD CONSTRAINT FK_gym_gymmember_TO_gym_ptapp -- 헬스장회원 -> PT신청
  FOREIGN KEY (
  member_no -- 헬스장회원번호
  )
  REFERENCES gym_gymmember ( -- 헬스장회원
  member_no -- 헬스장회원번호
  );

-- PT신청
ALTER TABLE gym_ptapp
  ADD CONSTRAINT FK_gym_trainer_TO_gym_ptapp -- 트레이너 -> PT신청
  FOREIGN KEY (
  trainer_no -- 트레이너번호
  )
  REFERENCES gym_trainer ( -- 트레이너
  member_no -- 트레이너번호
  );

-- 게시판
ALTER TABLE gym_board
  ADD CONSTRAINT FK_gym_member_TO_gym_board -- 회원 -> 게시판
  FOREIGN KEY (
  member_no -- 회원번호
  )
  REFERENCES gym_member ( -- 회원
  member_no -- 회원번호
  );

-- GX프로그램
ALTER TABLE gym_gxprogram
  ADD CONSTRAINT FK_gym_trainer_TO_gym_gxprogram -- 트레이너 -> GX프로그램
  FOREIGN KEY (
  member_no -- 트레이너번호
  )
  REFERENCES gym_trainer ( -- 트레이너
  member_no -- 트레이너번호
  );

-- GX신청
ALTER TABLE gym_gxapp
  ADD CONSTRAINT FK_gym_gxprogram_TO_gym_gxapp -- GX프로그램 -> GX신청
  FOREIGN KEY (
  gx_no -- GX번호
  )
  REFERENCES gym_gxprogram ( -- GX프로그램
  gx_no -- GX번호
  );

-- GX신청
ALTER TABLE gym_gxapp
  ADD CONSTRAINT FK_gym_gymmember_TO_gym_gxapp -- 헬스장회원 -> GX신청
  FOREIGN KEY (
  member_no -- 헬스장회원번호
  )
  REFERENCES gym_gymmember ( -- 헬스장회원
  member_no -- 헬스장회원번호
  );