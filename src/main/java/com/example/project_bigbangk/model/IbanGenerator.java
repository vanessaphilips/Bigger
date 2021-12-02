package com.example.project_bigbangk.model;

public class IbanGenerator {

    private String ACCOUNT_NUMBER_COMPLEATOR = "000";
    private String BANK_CODE = "BGBK";
    private String COUTRY_CODE = "NL";
    public int MIN_CHECK_NUMBER_VALUE = 10;
    public int MAX_CHECK_NUMBER_VALUE = 80;
    private int MAX_ACCOUNT_NUMBER = 8999999;
    private int MIN_ACCOUNT_NUMBER = 1000000;

    public String ibanGenerator() {
        String accountNumber = accountNumberGenerator();
        String checkNumber = checkNumberGenerator();
        return COUTRY_CODE + checkNumber + BANK_CODE + accountNumber;
    }

    public String accountNumberGenerator() {
        String generatetAccountNumberAsString = Integer.toString((int) (MAX_ACCOUNT_NUMBER * Math.random() + MIN_ACCOUNT_NUMBER));
        String accountNumberAsString = ACCOUNT_NUMBER_COMPLEATOR + generatetAccountNumberAsString;
        return accountNumberAsString;
    }

    private String checkNumberGenerator() {
        String checkNumber = Integer.toString((int) (MAX_CHECK_NUMBER_VALUE * Math.random() + MIN_CHECK_NUMBER_VALUE));
        return checkNumber;
    }
}
