CREATE TABLE IF NOT EXISTS `trains`
  (
     `train_id`          INTEGER NOT NULL auto_increment,
     `train_departingat` TIMESTAMP NOT NULL,
     PRIMARY KEY (`train_id`)
  );