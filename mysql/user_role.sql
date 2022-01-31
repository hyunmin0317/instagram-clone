CREATE TABLE 'user_role' (
    'id' INT(11) NOT NULL AUTO_INCREMENT,
    'user_id' INT(11) NOT NULL,
    'role_name' VARCHAR(100) NOT NULL,
    PRIMARY KEY ('id'),
    FOREIGN KEY ('user_id')
    REFERENCES 'user' ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;