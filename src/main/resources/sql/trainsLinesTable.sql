CREATE TABLE IF NOT EXISTS `trains_lines`
  (
     `train` INTEGER NOT NULL,
     `line`  INTEGER NOT NULL,
     PRIMARY KEY (`train`),
     KEY `fkidx_146` (`train`),
     CONSTRAINT `trains_to_train_lines` FOREIGN KEY `fkidx_146` (`train`)
     REFERENCES `trains` (`train_id`),
     KEY `fkidx_183` (`line`),
     CONSTRAINT `train_lines_to_trains` FOREIGN KEY `fkidx_183` (`line`)
     REFERENCES `trainlines` (`line_id`)
  );