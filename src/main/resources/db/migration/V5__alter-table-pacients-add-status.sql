alter table pacients add column status tinyint;
update pacients set status = 1;