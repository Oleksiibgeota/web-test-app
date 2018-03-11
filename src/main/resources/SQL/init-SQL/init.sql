CREATE TABLE user (
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    family VARCHAR(20) NOT NULL,
    gender CHAR(1),
    tel VARCHAR(13),
    salary MEDIUMINT NOT NULL,
    PRIMARY KEY (id)
    );