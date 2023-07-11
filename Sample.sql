UPDATE MEMBER
    SET USERPWD = '',
        EMAIL = '',
        PHONE = '',
        ADDRESS = ''
WHERE USERID = '';

UPDATE MEMBER SET USERPWD = 'sdfsdf', EMAIL = 'sdfsdf', PHONE = 'sdf', ADDRESS = 'sdf' WHERE USERID = 'sdfsdf';

select * from member;

delete from member where username = '김뫄뫄';

update member set userpwd = 'pass100' where username = '카리나';

commit;

rollback;

-- 4번. 회원 이름으로 검색 요청시 실행될 SQL문
SELECT * FROM MEMBER WHERE USERNAME LIKE '%?%'; --> 이렇게 했을 때는 잘 실행될까??
--> 보완

-- 5번. 회원 정보 변경 요청시 실행될 SQL문
UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?;

-- 6번. 회원 탈퇴 요청시 실행될 SQL문
DELETE FROM MEMBER WHERE USERID = ?;
