CREATE TABLE IF NOT EXISTS `trainlines`
  (
     `line_id`       INTEGER NOT NULL AUTO_INCREMENT,
     `line_distance` DOUBLE NOT NULL,
     `line_start`    VARCHAR(45) NOT NULL,
     `line_stop`     VARCHAR(45) NOT NULL,
     PRIMARY KEY (`line_id`)
  );