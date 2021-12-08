package com.example.project_bigbangk.service;

import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Karim Ajour on 2-12-2021 for Project_Big_Bangk
 */

@Service
public class IbanGeneratorService {

    private String ACCOUNT_NUMBER_COMPLEATOR = "000";
    private String BANK_CODE = "BGBK";
    private String COUTRY_CODE = "NL";
    public int MIN_CHECK_NUMBER_VALUE = 10;
    public int MAX_CHECK_NUMBER_VALUE = 80;
    private int MAX_ACCOUNT_NUMBER = 8999999;
    private int MIN_ACCOUNT_NUMBER = 1000000;
    private RootRepository rootRepository;


    @Autowired
    public IbanGeneratorService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public String getIban(){
        boolean ibanExists = true;
        String iban = ibanGenerator();
        while (ibanExists) {
            if (!ibanStringCheckForDoubles(iban)){
                ibanExists = false;
            } else {
               iban = ibanGenerator();
            }
        }
        return iban;
    }

    private String ibanGenerator() {
        String accountNumber = accountNumberGenerator();
        String checkNumber = checkNumberGenerator();
        return (COUTRY_CODE + checkNumber + BANK_CODE + accountNumber);
    }

    private String accountNumberGenerator() {
        String generatetAccountNumberAsString = Integer.toString((int) (MAX_ACCOUNT_NUMBER * Math.random() + MIN_ACCOUNT_NUMBER));
        String accountNumberAsString = ACCOUNT_NUMBER_COMPLEATOR + generatetAccountNumberAsString;
        return accountNumberAsString;
    }

    private String checkNumberGenerator() {
        String checkNumber = Integer.toString((int) (MAX_CHECK_NUMBER_VALUE * Math.random() + MIN_CHECK_NUMBER_VALUE));
        return checkNumber;
    }

    public boolean ibanStringCheckForDoubles(String iban) {
        if (rootRepository.findWalletByIban(iban) == null){
            return false;
        }
        return true;
    }
}
