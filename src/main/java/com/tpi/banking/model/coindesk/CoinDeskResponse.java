package com.tpi.banking.model.coindesk;

public class CoinDeskResponse {
	private CoinDeskTime time;
	private String disclaimer;
	private String chartName;
	private CoinDeskBpi bpi;
	public CoinDeskTime getTime() {
		return time;
	}
	public void setTime(CoinDeskTime time) {
		this.time = time;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public CoinDeskBpi getbpi() {
		return bpi;
	}
	public void setbpi(CoinDeskBpi bpi) {
		this.bpi = bpi;
	}
	
}
