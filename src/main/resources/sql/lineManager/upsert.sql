MERGE INTO `trainlines` (
    `line_id`,
    `line_start`,
    `line_distance`,
    `line_stop`
) KEY(`line_id`)
VALUES(?, ?, ?, ?);