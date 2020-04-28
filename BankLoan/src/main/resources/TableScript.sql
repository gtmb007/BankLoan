drop table if exists customer;
drop table if exists loan;

create table loan(
loan_id INT(6)  AUTO_INCREMENT primary key,
loan_amount INT(8) not null,
term INT(4) not null,
loan_type varchar(25) not null,
interest_rate INT(4)not null
);

insert into loan(loan_id, loan_amount, term, loan_type, interest_rate) values(1001,1200000,15,'HomeLoan',13);
insert into loan(loan_id, loan_amount, term, loan_type, interest_rate) values(1002,700000,5,'CarLoan',9);
insert into loan(loan_id, loan_amount, term, loan_type, interest_rate) values(1003,900000,5,'CarLoan',9);
insert into loan(loan_id, loan_amount, term, loan_type, interest_rate) values(1004,1500000,15,'HomeLoan',13);
insert into loan(loan_id, loan_amount, term, loan_type, interest_rate) values(1005,2200000,10,'HomeLoan',9);

create table customer(
customer_id INT(6) primary key,
name varchar(25) not null,
mobile_no BIGINT(10) not null,
loan_id INT(8) references loan(loan_id) 
); 

insert into customer(customer_id, name, mobile_no, loan_id) values(2001,'Gautam',9123833866,1001);
insert into customer(customer_id, name, mobile_no, loan_id) values(2002,'Bharadwaj',9163217664,1002);
insert into customer(customer_id, name, mobile_no, loan_id) values(2003,'Rohit',9334518830,1003);
insert into customer(customer_id, name, mobile_no, loan_id) values(2004,'Kumar',9801554349,null);
insert into customer(customer_id, name, mobile_no, loan_id) values(2005,'Gautam Bharadwaj',9123833866,1004);
insert into customer(customer_id, name, mobile_no, loan_id) values(2006,'Rohit Kumar',9334518830,1005);

select * from customer;

select * from loan;

commit;
