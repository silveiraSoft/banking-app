set foreign_key_checks = 0;
delete from account;

set foreign_key_checks = 1;
alter table account auto_increment = 1;

INSERT IGNORE INTO banking_app.account
(balance, account_holder_name)
VALUES(1000.0, 'adalbertónño-cheking');
INSERT IGNORE INTO banking_app.account
(balance, account_holder_name)
VALUES(2000.1, 'yilian-cheking');
INSERT IGNORE INTO banking_app.account
(balance, account_holder_name)
VALUES(0.0, 'yilian-saving');
