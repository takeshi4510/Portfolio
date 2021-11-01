 CREATE TABLE bbs (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  title char(30) NOT NULL,
  name char(30) NOT NULL,
  contents varchar(255) NOT NULL,
  add_date timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  up_date timestamp NULL DEFAULT NULL,
  PRIMARY KEY (id),
  KEY id (id)