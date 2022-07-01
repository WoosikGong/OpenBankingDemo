create database OPENBANK;

create table bnkTransaction(
	trId char(20) not null,
	srcAccount char(30),
	dstAccount char(30),
	srcBnkCode char(3),
	dstBnkCode char(3),
	trType char(50),
	finAccount char(30),
	amount int,
	primary key (trId)
);

create table TestTable (
    TestColumn varchar(255),
    TestColumn2 varchar(255)
);

insert into TestTable(TestColumn, TestColumn2) values('test', 'test');
insert into TestTable(TestColumn, TestColumn2) values('test2', 'test2');