create table gym_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer int not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now(),
  category int not null
);

alter table gym_board
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;
  
create table gym_member(
  member_no int not null,
  name varchar(20) not null,
  phone_number varchar(20) not null,
  age int not null,
  password varchar(100) not null,
  per int not null,
  created_date date default (current_date())
);

alter table gym_member
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;

  alter table gym_member
  add constraint gym_member_uk unique (phone_number);
  
-- 게시판 작성자에 대해 외부키 설정
alter table gym_board
  add constraint gym_board_fk foreign key (writer) references gym_member (member_no);
  

  
  
  
  