-- gym_member 테이블 예제 데이터
insert into gym_member(member_no, name, phone_number,age, password) 
  values(1, 'trainer', '01011111111', 20, sha1('1111'));
  insert into gym_member(member_no, name, phone_number,age, password) 
  values(2, 'gymmember', '01022222222', 30, sha1('1111'));
  insert into gym_member(member_no, name, phone_number,age, password) 
  values(3, 'manager', '01022222222', 35, sha1('1111'));
  
  -- gym_trainer 테이블 예제 데이터
  insert into gym_trainer(member_no, name, school, major,gtno, hr_pay) 
  values(1, 'trainer', '한체대', '물리치료학과', 1);


  -- gym_board 테이블 예제 데이터
insert into gym_board(board_no, title, content, writer, password, category)
  values(11, '제목1', '내용', 1, sha1('1111'), 1);
insert into gym_board(board_no, title, content, writer, password, category)
  values(12, '제목2', '내용', 1, sha1('1111'), 1);
insert into gym_board(board_no, title, content, writer, password, category)
  values(13, '제목3', '내용', 3, sha1('1111'), 1);