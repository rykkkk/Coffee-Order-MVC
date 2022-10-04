CREATE TABLE theOrder
(
    id         LONG Primary Key AUTO_INCREMENT,
    numCup     INTEGER(10) NOT NULL,
    size       VARCHAR(64) NOT NULL,
    coffeeType VARCHAR(50),
    numSugar   INTEGER(10),
    numCream   INTEGER(10)
);