CREATE TABLE comment (
    id INT(11) NOT NULL AUTO_INCREMENT,
    user_id INT(11) NOT NULL,
    post_id INT(11) NOT NULL, 
    user_name VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    date DATETIME NULL DEFAULT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id)
    REFERENCES user (id),
    FOREIGN KEY (post_id)
    REFERENCES post (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;