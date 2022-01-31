CREATE TABLE 'post' (
    'id' INT(11) NOT NULL AUTO_INCREMENT,
    'user_id' INT(11) NOT NULL,
    'title' VARCHAR(100) NOT NULL,
    'content' VARCHAR(255) NOT NULL,
    'image' VARCHAR(100) NOT NULL,
    'create_date' DATETIME NULL DEFAULT NULL,
    'modify_date' DATETIME NULL DEFAULT NULL,
    PRIMARY KEY('id')
    FOREIGN KEY ('user_id')
    REFERENCES 'user' ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;