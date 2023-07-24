-- myapp_member 테이블 예제 데이터
insert into gym_member(member_no, name, phone_number, age, password, per) 
  values(1, 'aaa', '01011111111', 20, sha1('1111'), 1);
insert into gym_member(member_no, name, age, password, per) 
  values(2, 'bbb', '01022222222', 30, sha1('1111'), 3);
insert into gym_member(member_no, name, age, password, per)  
  values(3, 'ccc', '01033333333', 40, sha1('1111'), 6);
insert into gym_member(member_no, name, age, password, per) 
  values(4, 'ddd', '01044444444', 50, sha1('1111'), 3);
insert into gym_member(member_no, name, age, password, per) 
  values(5, 'eee', '01055555555', 60, sha1('1111'), 6);
insert into gym_member(member_no, name, age, password, per) 
  values(6, 'fff', '01066666666', 70, sha1('1111'), 1);

-- myapp_board 테이블 예제 데이터
insert into gym_board(board_no, title, content, writer, password, category)
  values(11, '제목1', '내용', 1, sha1('1111'), 1);
insert into gym_board(board_no, title, content, writer, password, category)
  values(12, '제목2', '내용', 1, sha1('1111'), 1);
insert into gym_board(board_no, title, content, writer, password, category)
  values(13, '제목3', '내용', 3, sha1('1111'), 1);
insert into gym_board(board_no, title, content, writer, password, category)
  values(14, '제목4', '내용', 4, sha1('1111'), 1);
insert into gym_board(board_no, title, content, writer, password, category)
  values(15, '제목5', '내용', 5, sha1('1111'), 2);
insert into gym_board(board_no, title, content, writer, password, category)
  values(16, '제목6', '내용', 5, sha1('1111'), 2);
insert into gym_board(board_no, title, content, writer, password, category)
  values(17, '제목7', '내용', 5, sha1('1111'), 2);