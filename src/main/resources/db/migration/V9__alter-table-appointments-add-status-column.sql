alter table appointments add column status tinyint;
update appointments set status = 1;