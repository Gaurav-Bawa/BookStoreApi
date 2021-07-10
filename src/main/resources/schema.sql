drop table if exists book;
drop table if exists promotion;
create table book (isbn bigint not null, author varchar(255), description varchar(255), name varchar(255), price double precision not null, type varchar(255), primary key (isbn));
create table promotion (promo_code varchar(100) not null, book_type varchar(100) not null, percent double precision not null, primary key (promo_code));