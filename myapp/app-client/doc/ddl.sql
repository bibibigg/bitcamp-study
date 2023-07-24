create table myapp_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer varchar(20) not null,
  password varchar(100) null,
  view_count int default 0,
<<<<<<< HEAD
  create_date datetime default now()
=======
  created_date datetime default now()
>>>>>>> a2d5e80f777e7e289e43b38904d2414776944cfb
);

alter table myapp_board
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;
  
<<<<<<< HEAD
  create table myapp_member (
=======
create table myapp_member(
>>>>>>> a2d5e80f777e7e289e43b38904d2414776944cfb
  member_no int not null,
  name varchar(20) not null,
  email varchar(50) not null,
  password varchar(100) not null,
  gender char(1) not null
<<<<<<< HEAD
  );
  
  alter table myapp_member
=======
);

alter table myapp_member
>>>>>>> a2d5e80f777e7e289e43b38904d2414776944cfb
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;
  
  
<<<<<<< HEAD
  -- 게시판에 카테고리 컬럼 추가
  alter table myapp_board
  add column category int not null;
  

=======
-- 게시판에 카테고리 컬럼 추가
alter table myapp_board
  add column category int not null;
  
  
>>>>>>> a2d5e80f777e7e289e43b38904d2414776944cfb
  
  
  