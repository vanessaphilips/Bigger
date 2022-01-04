INSERT INTO `bigbangk`.`wallet` (`IBAN`, `balance`) VALUES ('NL10BGBK0000000001', '5000000');
INSERT INTO `bigbangk`.`bank` (`code`, `name`, `IBAN`) VALUES ('BGBK', 'BigBangk', 'NL10BGBK0000000001');
INSERT INTO `bigbangk`.`wallet_has_asset` (`code`, `amount`, `IBAN`) VALUES ('ADA', '500', 'NL10BGBK0000000001');
INSERT INTO `bigbangk`.`wallet_has_asset` (`code`, `amount`, `IBAN`) VALUES ('AVAX', '5000', 'NL10BGBK0000000001');
INSERT INTO `bigbangk`.`wallet_has_asset` (`code`, `amount`, `IBAN`) VALUES ('BNB', '5000', 'NL10BGBK0000000001');
INSERT INTO `bigbangk`.`wallet_has_asset` (`code`, `amount`, `IBAN`) VALUES ('BTC', '50', 'NL10BGBK0000000001');