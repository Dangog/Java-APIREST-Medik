alter table medics add column status tinyint;
update medics set status = 1;