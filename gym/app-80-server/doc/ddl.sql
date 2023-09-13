create table gym_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer varchar(20) not null,
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
  age int not null,
  password varchar(100) not null,
  per int not null,
  created_date date default (current_date()),
  calculated_EndDate date not null
);

alter table gym_member
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;
  
  
  
  
  