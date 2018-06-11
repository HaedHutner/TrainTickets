CREATE TABLE IF NOT EXISTS `tickets`
(
    `ticket_id` INTEGER NOT NULL AUTO_INCREMENT,
    `ticket_train` INTEGER NOT NULL,
    PRIMARY KEY (`ticket_train`),
    FOREIGN KEY (`train_id`) REFERENCES `trains` (`train_id`) ON DELETE CASCADE
);