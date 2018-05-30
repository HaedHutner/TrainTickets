CREATE TABLE IF NOT EXISTS `trains`
  (
     `train_id`          INTEGER NOT NULL AUTO_INCREMENT,
     `train_departingat` TIMESTAMP NOT NULL,
     `train_route`       INTEGER NOT NULL,
     PRIMARY KEY (`train_id`),
     FOREIGN KEY (`train_route`) REFERENCES `trainlines` (`line_id`) ON DELETE CASCADE
  );