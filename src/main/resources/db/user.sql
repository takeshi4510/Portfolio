CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name char(30) NOT NULL,
  pass char(30) NOT NULL,
  add_date timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  up_date timestamp NULL DEFAULT NULL,
  PRIMARY KEY (id)