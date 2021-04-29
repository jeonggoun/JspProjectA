--게시판( 글번호, 내용, 아이디,ㅁ 번화, 글 작성자,
create table memberBoard(
  Board_num number primary key not null,
  Board_id varchar2(15),
  Board_subject varchar2(50),
  Board_content varchar2(2000),
  Board_file varchar2(20),
  Board_re_ref number,
  Board_re_lev number,
  Board_re_seq number,
  Board_readcount number,
  Board_date date
);

select * from memberBoard;

--제약조건 추가
alter table memberBoard
  add constraint pk_board_id foreign key(Board_id)
    references boardMember(Member_id);