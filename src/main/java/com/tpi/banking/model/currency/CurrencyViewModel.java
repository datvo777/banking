package com.tpi.banking.model.currency;

import java.util.List;

public class CurrencyViewModel {
	private String updateTime;
	private List<CurrencyDTO> currencies;
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public List<CurrencyDTO> getCurrencies() {
		return currencies;
	}
	public void setCurrencies(List<CurrencyDTO> currencies) {
		this.currencies = currencies;
	}
	
}
