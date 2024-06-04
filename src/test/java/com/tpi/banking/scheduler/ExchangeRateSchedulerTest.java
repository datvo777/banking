package com.tpi.banking.scheduler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tpi.banking.model.currency.CurrencyViewModel;
import com.tpi.banking.service.CoinDeskService;
import com.tpi.banking.service.CurrencyService;

class ExchangeRateSchedulerTest {
	@Mock
    private CurrencyService currencyService;

    @Mock
    private CoinDeskService coinDeskService;

    @InjectMocks
    private ExchangeRateScheduler exchangeRateScheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateExchangeRatesScheduled() {
        CurrencyViewModel currencyViewModel = mock(CurrencyViewModel.class);
        when(coinDeskService.getCurrentCurrencies()).thenReturn(currencyViewModel);
        when(currencyViewModel.getCurrencies()).thenReturn(Collections.emptyList());

        exchangeRateScheduler.updateExchangeRatesScheduled();

        verify(coinDeskService, times(1)).getCurrentCurrencies();
        verify(currencyService, times(1)).updateMultipleCurrencies(Collections.emptyList());
    }
}
