-- users

-- insert
insert INTO USERS VALUES(user_seq.nextval , '이하민', 'lhm8446@gmail.com', '1234', 'male');

-- delete
delete from users;

commit;

--select 
select no,name from users where email = 'lhm8446@gmail.com' and password = '1234';

select * from users;

select no,name,email,password,gender from users where no = 2;

--update 
update users set name = '이하민',password = '1234',gender = 'male' where no = 2;
