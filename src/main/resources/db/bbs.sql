CREATE TABLE bbs (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  title char(30) NOT NULL,
  name char(30) NOT NULL,
  contents varchar(255) NOT NULL,
  add_date datetime DEFAULT NULL,
  up_date datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY id (id)
