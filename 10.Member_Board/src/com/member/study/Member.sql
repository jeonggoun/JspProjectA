--회원관리(아이디 패스워드 등)
create table boardMember(
  Member_id varchar2(15) primary key not null,
  Member_pw  varchar2(15),
  Member_name  varchar2(15),
  Member_age number,
  Member_gender varchar2(5),
  Member_email varchar2(30)
);

select * from boardMember;