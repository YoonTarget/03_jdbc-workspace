UPDATE MEMBER
    SET USERPWD = '',
        EMAIL = '',
        PHONE = '',
        ADDRESS = ''
WHERE USERID = '';

UPDATE MEMBER SET USERPWD = 'sdfsdf', EMAIL = 'sdfsdf', PHONE = 'sdf', ADDRESS = 'sdf' WHERE USERID = 'sdfsdf';

select * from member;

delete from member where username = '�����';

update member set userpwd = 'pass100' where username = 'ī����';

commit;

rollback;

-- 4��. ȸ�� �̸����� �˻� ��û�� ����� SQL��
SELECT * FROM MEMBER WHERE USERNAME LIKE '%?%'; --> �̷��� ���� ���� �� ����ɱ�??
--> ����

-- 5��. ȸ�� ���� ���� ��û�� ����� SQL��
UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?;

-- 6��. ȸ�� Ż�� ��û�� ����� SQL��
DELETE FROM MEMBER WHERE USERID = ?;
