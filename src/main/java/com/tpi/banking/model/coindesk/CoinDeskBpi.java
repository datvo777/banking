package com.tpi.banking.model.coindesk;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinDeskBpi {
	@JsonProperty("USD")
    private CoinDeskCurrency usd;

    @JsonProperty("GBP")
    private CoinDeskCurrency gbp;

    @JsonProperty("EUR")
    private CoinDeskCurrency eur;

	public CoinDeskCurrency getUsd() {
		return usd;
	}

	public void setUsd(CoinDeskCurrency usd) {
		this.usd = usd;
	}

	public CoinDeskCurrency getGbp() {
		return gbp;
	}

	public void setGbp(CoinDeskCurrency gbp) {
		this.gbp = gbp;
	}

	public CoinDeskCurrency getEur() {
		return eur;
	}

	public void setEur(CoinDeskCurrency eur) {
		this.eur = eur;
	}
}
