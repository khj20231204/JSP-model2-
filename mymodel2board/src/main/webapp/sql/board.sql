--모델2 게시판
x
select * from seq;
select * from model2board;

insert into model2board values(model2board_seq.nextval,'홍길동','1234','모델2게시판',
'게시판 내용',model2board_seq.nextval,0,0,0,sysdate);

select count(*) from model2board;

create table model2board(
board_num number primary key,
board_name varchar2(20),
board_pass varchar2(15),
board_subject varchar2(50),
board_content varchar2(2000),
board_re_ref number, -- 글 그룹번호
board_re_lev number, -- 댓글 깊이 : 원문(0), 1, 2...
board_re_seq number, -- 댓글 출력 순서 : 원문(0) 오름차순 정렬
board_readcount number,
board_date timestamp );

create sequence model2board_seq
start with 1
increment by 1
nocache;