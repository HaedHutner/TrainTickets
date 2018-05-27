CREATE TABLE IF NOT EXISTS `tickets`
  (
     `ticket_id`    INTEGER NOT NULL auto_increment,
     `ticket_price` DOUBLE NOT NULL,
     `ticket_train` INTEGER NOT NULL,
     PRIMARY KEY (`ticket_id`),
     KEY `fkidx_164` (`ticket_train`),
     CONSTRAINT `tickets_to_trains` FOREIGN KEY `fkidx_164` (`ticket_train`) REFERENCES `trains` (`train_id`)
  );