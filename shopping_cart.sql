Shopping Cart - dec2016


CREATE TABLE login 
( 
u_id int primary key,
username varchar(20) not null, 
password varchar(25) not null 
); 

create table product_list
(
p_id int primary key,
p_name varchar(25) not null,
p_desc LONGTEXT,
p_price double not null,
p_quantity int,
p_photo longblob 
);
ALTER TABLE product_list drop column p_quantity;

select * from product_list;
select * from login;

create table cart_list
(
c_uid int ,
p_uid int
)

create table wish_list
(
w_uid int ,
w_pid int
)

drop table login;
drop table product_list;
drop table cart_list;
drop table wish_list;


ALTER TABLE login modify u_id int AUTO_INCREMENT;
ALTER TABLE product_list modify p_id int AUTO_INCREMENT;

ALTER TABLE cart_list
ADD FOREIGN KEY (c_uid)
REFERENCES login(u_id)

ALTER TABLE cart_list
ADD FOREIGN KEY (p_uid)
REFERENCES product_list(p_id)

ALTER TABLE wish_list
ADD FOREIGN KEY (w_uid)
REFERENCES login(u_id)

ALTER TABLE wish_list
ADD FOREIGN KEY (w_pid)
REFERENCES product_list(p_id)

----------------------------------------
insert into login(username,password) values 
(  
'admin', 
'admin' 
);
