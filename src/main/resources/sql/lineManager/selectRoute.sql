SELECT trainlines.*
FROM trainlines
JOIN trains_lines
    ON trainlines.line_id = trains_lines.line
JOIN trains
    ON trains_lines.train = trains.train_id
    AND trains.train_id = ?;