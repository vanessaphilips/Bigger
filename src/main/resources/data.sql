INSERT INTO `Client`
VALUES ('michael@oosterhout.nl', 'Michael', NULL, 'Oosterhout', '1965-10-10', '123456789', 'michaeloosterhout', null,
        null, null),
       ('gerke@deboer.nl', 'Gerke', 'de', 'Boer', '1959-03-02', '123456789', 'gerkedeboer', null, null, null),
       ('huub@vanthienen.nl', 'Huub', 'van', 'Thienen', '1965-11-11', '123456789', 'huubvanthienen', null, null, null),
       ('michel@oey.nl', 'Michel', NULL, 'Oey', '1968-09-09', '123456789', 'micheloey', null, null, null),
       ('sander@deboer.nl', 'Sander', 'de', 'Boer', '1966-09-09', '123456789', 'sanderdeboer', null, null, null),
       ('nicole@wong.nl', 'Nicole', NULL, 'Wong', '1973-01-01', '123456789', 'nicolewong', null, null, null);

INSERT INTO `bank`
VALUES ('BGBK', 'Big Bangk', 'NL17 BGBK 7265511', 1000.00, 5.0);

INSERT INTO `Wallet`
VALUES ('NL20BGBK0001234567', 10000.00);

INSERT INTO `wallet_has_asset`
VALUES ('BTC', 10, 'NL20BGBK0001234567'),
       ('ADA', 10, 'NL20BGBK0001234567'),
       ('ETH', 10, 'NL20BGBK0001234567'),
       ('DOGE', 10, 'NL20BGBK0001234567'),
       ('LUNA', 10, 'NL20BGBK0001234567');

INSERT INTO `pricehistory`
VALUES ('2005-12-31 21:00:59Z', 'BTC', 44000),
       ('2005-12-31 21:05:59Z', 'BTC', 44200),
       ('2005-12-31 21:10:59Z', 'BTC', 44370),
       ('2005-12-31 21:15:59Z', 'BTC', 44880),
       ('2005-12-31 21:20:59Z', 'BTC', 44332),
       ('2005-12-31 21:00:59Z', 'ETH', 2.1),
       ('2005-12-31 21:05:59Z', 'ETH', 2.6),
       ('2005-12-31 21:10:59Z', 'ETH', 2.7),
       ('2005-12-31 21:15:59Z', 'ETH', 2.0),
       ('2005-12-31 21:20:59Z', 'ETH', 2.5);
INSERT INTO `asset`
VALUES('BTC', 'Bitcoin'),
       ('ETH', 'Ethereal')

