CREATE TABLE IF NOT EXISTS `trains`
  (
     `train_id`          INTEGER NOT NULL AUTO_INCREMENT,
     `train_departingat` TIMESTAMP NOT NULL,
     PRIMARY KEY (`train_id`)
  );