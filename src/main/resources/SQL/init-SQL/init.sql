CREATE TABLE user (
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    family VARCHAR(20) NOT NULL,
    gender CHAR(1),
    tel VARCHAR(13),
    salary MEDIUMINT NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE car (
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE user_car(
    user_id MEDIUMINT,
    car_id MEDIUMINT,
    PRIMARY KEY (user_id, car_id),
    FOREIGN KEY (user_id) REFERENCES user(id) ON UPDATE CASCADE,
    FOREIGN KEY (car_id) REFERENCES car(id) ON UPDATE CASCADE
);