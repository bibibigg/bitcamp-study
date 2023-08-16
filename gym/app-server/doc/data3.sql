-- gym_member 테이블 예제 데이터
insert into gym_member(member_no, name, phone_number, age, password) 
  values(1, 'aaa', '01011111111', 20, sha1('1111'));
insert into gym_member(member_no, name, phone_number, age, password) 
  values(2, 'bbb', '01022222222', 30, sha1('1111'));
insert into gym_member(member_no, name, phone_number, age, password) 
  values(3, 'ccc', '01033333333', 40, sha1('1111'));
insert into gym_member(member_no, name, phone_number, age, password) 
  values(4, 'ddd', '01044444444', 50, sha1('1111'));
insert into gym_member(member_no, name, phone_number, age, password) 
  values(5, 'eee', '01055555555', 60, sha1('1111'));
insert into gym_member(member_no, name, phone_number, age, password) 
  values(6, 'fff', '01066666666', 70, sha1('1111'));

  
-- gym_board_category 테이블 예제 데이터
insert into gym_board_category(board_category_no, name) values(1, '게시판');
insert into gym_board_category(board_category_no, name) values(2, '독서록');
  
-- gym_board 테이블 예제 데이터
insert into gym_board(board_no, title, content, writer, category)
  values(11, '제목1', '내용', 1, 1);
insert into gym_board(board_no, title, content, writer, category)
  values(12, '제목2', '내용', 1, 1);
insert into gym_board(board_no, title, content, writer, category)
  values(13, '제목3', '내용', 3, 1);
insert into gym_board(board_no, title, content, writer, category)
  values(14, '제목4', '내용', 4, 1);
insert into gym_board(board_no, title, content, writer, category)
  values(15, '제목5', '내용', 5, 2);
insert into gym_board(board_no, title, content, writer, category)
  values(16, '제목6', '내용', 5, 2);
insert into gym_board(board_no, title, content, writer, category)
  values(17, '제목7', '내용', 5, 2);