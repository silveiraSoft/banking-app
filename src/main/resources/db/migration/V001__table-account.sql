CREATE TABLE banking_app.account (
                           id bigint NOT NULL AUTO_INCREMENT,
                           account_holder_name varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                           balance double NOT NULL DEFAULT '0',
                           PRIMARY KEY (id),
                           UNIQUE KEY idx_account_account_holder_name (account_holder_name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
