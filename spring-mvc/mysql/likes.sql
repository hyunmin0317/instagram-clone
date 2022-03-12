CREATE TABLE likes (
    id INT(11) NOT NULL AUTO_INCREMENT,
    post_id INT(11) NOT NULL,
    user_id INT(11) NOT NULL,
    FOREIGN KEY (post_id)
    REFERENCES post (id),
    FOREIGN KEY (user_id)
    REFERENCES user (id),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;