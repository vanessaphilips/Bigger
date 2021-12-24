package com.example.project_bigbangk.service.priceHistoryUpdate;

import com.example.project_bigbangk.model.PriceHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CryptoApiSwitcherServiceTest {

    private ICryptoApiSwitcherStrategy cryptoApiSwitcherService = new CryptoApiSwitcherService();

    @BeforeEach
    void setup() {
        ICryptoApiNegotiatorService cryptoApiNegotiatorService1 = Mockito.mock(ICryptoApiNegotiatorService.class);
        ICryptoApiNegotiatorService cryptoApiNegotiatorService2 = Mockito.mock(ICryptoApiNegotiatorService.class);
        ICryptoApiNegotiatorService cryptoApiNegotiatorService3 = Mockito.mock(ICryptoApiNegotiatorService.class);
        ICryptoApiNegotiatorService cryptoApiNegotiatorService4 = Mockito.mock(ICryptoApiNegotiatorService.class);
        cryptoApiSwitcherService.addNegotiator(cryptoApiNegotiatorService1);
        cryptoApiSwitcherService.addNegotiator(cryptoApiNegotiatorService2);
        cryptoApiSwitcherService.addNegotiator(cryptoApiNegotiatorService3);
        cryptoApiSwitcherService.addNegotiator(cryptoApiNegotiatorService4);

        Mockito.when(cryptoApiNegotiatorService1.isAvailable()).thenReturn(false);
        Mockito.when(cryptoApiNegotiatorService2.isAvailable()).thenReturn(true);
        Mockito.when(cryptoApiNegotiatorService3.isAvailable()).thenReturn(true);
        Mockito.when(cryptoApiNegotiatorService4.isAvailable()).thenReturn(false);

        PriceHistory priceHistory = Mockito.mock(PriceHistory.class);

        List<PriceHistory> priceHistoryList1 = createListPRiceHistory(1, priceHistory);
        List<PriceHistory> priceHistoryList2 = createListPRiceHistory(2, priceHistory);
        List<PriceHistory> priceHistoryList3 = createListPRiceHistory(3, priceHistory);
        List<PriceHistory> priceHistoryList4 = createListPRiceHistory(4, priceHistory);

        Mockito.when(cryptoApiNegotiatorService1.getPriceHistory("EUR")).thenReturn(priceHistoryList1);
        Mockito.when(cryptoApiNegotiatorService2.getPriceHistory("EUR")).thenReturn(priceHistoryList2);
        Mockito.when(cryptoApiNegotiatorService3.getPriceHistory("EUR")).thenReturn(priceHistoryList3);
        Mockito.when(cryptoApiNegotiatorService4.getPriceHistory("EUR")).thenReturn(priceHistoryList4);
    }

    private List<PriceHistory> createListPRiceHistory(int listSize, PriceHistory priceHistory) {
        List<PriceHistory> priceHistoryList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            priceHistoryList.add(priceHistory);
        }
        return priceHistoryList;
    }

    @Test
    void getAvailableCryptoService() {
        ICryptoApiNegotiatorService actual = cryptoApiSwitcherService.getAvailableCryptoService();
        assertEquals(2, actual.getPriceHistory("EUR").size());
    }
}