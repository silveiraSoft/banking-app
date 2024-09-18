
set foreign_key_checks = 0;
delete from account;

set foreign_key_checks = 1;
alter table account auto_increment = 1;



