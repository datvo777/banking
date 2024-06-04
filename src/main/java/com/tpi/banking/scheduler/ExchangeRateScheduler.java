package com.tpi.banking.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tpi.banking.model.currency.CurrencyViewModel;
import com.tpi.banking.service.CoinDeskService;
import com.tpi.banking.service.CurrencyService;

@Component
public class ExchangeRateScheduler {

	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private CoinDeskService coinDeskService;

	@Scheduled(fixedRate = 10000)
	public void updateExchangeRatesScheduled() {
		CurrencyViewModel currencyViewModel = coinDeskService.getCurrentCurrencies();
		currencyService.updateMultipleCurrencies(currencyViewModel.getCurrencies());
	}
}
