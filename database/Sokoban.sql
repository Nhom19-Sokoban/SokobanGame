create database Sokoban

create table LevelSokoban
(idLV varchar(20) not null,
tenLV nvarchar(10) not null,
tenfile nvarchar(50),
cot int,
dong int
)

alter table LevelSokoban add constraint PKtenLV primary key(tenLV)

CREATE TABLE TaiKhoan (
    username NVARCHAR(50) NOT NULL,
    password NVARCHAR(50) NOT NULL,
    role NVARCHAR(50) 
)

alter table TaiKhoan add constraint PKusername primary key(username)



-- them tai khoan
INSERT INTO TaiKhoan (username, password, role)
VALUES ('admin', '123', 'admin');


select*from TaiKhoan
--them man choi
insert into LevelSokoban
values
('lv01','Level 1', 'level1.txt',8, 9),
('lv02','Level 2', 'level2.txt',8, 9),
('lv03','Level 3', 'level3.txt',8, 9),
('lv04','Level 4', 'level4.txt',10, 6),
('lv05','Level 5', 'level5.txt',12, 8),
('lv06','Level 6', 'level6.txt',15, 10),
('lv07','Level 7', 'level7.txt',18, 10),
('lv08','Level 8', 'level8.txt',24, 13),
('lv09','Level 9', 'level9.txt',21, 13),
('lv010','Level 10', 'level10.txt',13, 11);

ALTER TABLE LevelSokoban
ADD CONSTRAINT CK_Cot CHECK (cot BETWEEN 3 AND 25),
    CONSTRAINT CK_Dong CHECK (dong BETWEEN 3 AND 25);






















CREATE PROCEDURE SP_timLevelTheoTen
    @tenLV NVARCHAR(10)
AS
BEGIN
    SELECT * FROM LevelSokoban
    WHERE tenLV = @tenLV
END

CREATE PROCEDURE SP_lietkeALLLevelSokoban
AS
BEGIN
    SELECT * FROM LevelSokoban
END

    SELECT * FROM LevelSokoban

SELECT * FROM sys.objects
WHERE type = 'P' AND name = 'SP_lietkeALLLevelSokoban';

EXEC dbo.SP_lietkeALLLevelSokoban;
EXEC SP_lietkeALLLevelSokoban;


delete from LevelSokoban

drop table LevelSokoban