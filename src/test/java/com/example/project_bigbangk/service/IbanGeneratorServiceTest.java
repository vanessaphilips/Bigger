package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Wallet;
import com.example.project_bigbangk.repository.RootRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Karim Ajour on 8-12-2021 for Project_Big_Bangk
 */

class IbanGeneratorServiceTest {

    @Test
    void ibanGeneratorLengthTest() {
        int expected = 18;
        RootRepository mockRootRepository = Mockito.mock(RootRepository.class);

        IbanGeneratorService serviceUnderTest = new IbanGeneratorService(mockRootRepository);

        int actual = serviceUnderTest.getIban().length();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void accountNumberGeneratorBetweenMinAndMaxValue() {
        ArrayList<Integer> accountNumberList = new ArrayList<>();
        RootRepository mockRootRepository = Mockito.mock(RootRepository.class);
        int expectedMaxHighestNumber = 9999999;
        int expectedMinLowestNumber = 1000000;
        int actualMaxNumber = 0;
        int actualMinNumber = 10000000;

        IbanGeneratorService serviceUnderTest = new IbanGeneratorService(mockRootRepository);
        for (int i = 0; i < 100000; i++) {
           String accountNumber = serviceUnderTest.getIban();
           accountNumber = accountNumber.substring(8);
           Integer number = Integer.valueOf(accountNumber);
           accountNumberList.add(number);
        }

        for (Integer accountNumber : accountNumberList) {
            if (accountNumber < actualMinNumber) {
                actualMinNumber = accountNumber;
            }
            if (accountNumber > actualMaxNumber) {
                actualMaxNumber = accountNumber;
            }
        }

        assertTrue(expectedMinLowestNumber <= actualMinNumber && actualMaxNumber <= expectedMaxHighestNumber);
    }

    @Test
    void checkNumberGeneratorBetweenMinAndMaxValue() {
        ArrayList<Integer> checkNumberList = new ArrayList<>();
        RootRepository mockRootRepository = Mockito.mock(RootRepository.class);
        int expectedMaxHighestNumber = 89;
        int expectedMinLowestNumber = 10;
        int actualMaxNumber = 0;
        int actualMinNumber = 90;

        IbanGeneratorService serviceUnderTest = new IbanGeneratorService(mockRootRepository);
        for (int i = 0; i < 10000; i++) {
            String checkNumber = serviceUnderTest.getIban();
            checkNumber = checkNumber.substring(2, 4);
            Integer number = Integer.valueOf(checkNumber);
            checkNumberList.add(number);
        }

        for (Integer checkNumber : checkNumberList) {
            if (checkNumber < actualMinNumber) {
                actualMinNumber = checkNumber;
            }
            if (checkNumber > actualMaxNumber) {
                actualMaxNumber = checkNumber;
            }
        }

        assertTrue(expectedMinLowestNumber <= actualMinNumber && actualMaxNumber <= expectedMaxHighestNumber);
    }

    @Test
    void ibanStringCheckForDoubles() {
        Wallet walletBestaat = new Wallet("NL11BGBK0001234567", 10000);
        RootRepository mockRootRepository = Mockito.mock(RootRepository.class);

        Mockito.when(mockRootRepository.findWalletByIban("NL11BGBK0001234567")).thenReturn(walletBestaat);

        IbanGeneratorService serviceUnderTest = new IbanGeneratorService(mockRootRepository);
        boolean actual = serviceUnderTest.ibanStringCheckForDoubles("NL11BGBK0001234567");
        assertTrue(actual);

    }
}