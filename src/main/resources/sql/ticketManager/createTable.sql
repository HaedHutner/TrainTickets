CREATE TABLE IF NOT EXISTS `tickets`
(
    `ticket_id` INTEGER NOT NULL AUTO_INCREMENT,
    `ticket_price` DOUBLE NOT NULL,
    `ticket_train` INTEGER NOT NULL,
    PRIMARY KEY (`ticket_id`),
    FOREIGN KEY (`ticket_train`) REFERENCES `trains` (`train_id`) ON DELETE CASCADE
);