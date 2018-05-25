CREATE TABLE IF NOT EXISTS `trains_lines`
  (
     `train` INTEGER NOT NULL,
     `line`  INTEGER NOT NULL,
     FOREIGN KEY (`train`) REFERENCES `trains` (`train_id`),
     FOREIGN KEY (`line`) REFERENCES `trainlines` (`line_id`)
  );