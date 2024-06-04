package com.tpi.banking.model.currency;

public class CurrencyDTO {
	private String code;
	private String name;
	private float exchangeRate;
	private String updateTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public CurrencyDTO() {
	}

	private CurrencyDTO(Builder builder) {
		this.code = builder.code;
		this.name = builder.name;
		this.exchangeRate = builder.exchangeRate;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		private String code;
		private String name;
		private float exchangeRate;
		private String updateTime;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder exchangeRate(float exchangeRate) {
			this.exchangeRate = exchangeRate;
			return this;
		}

		public Builder updateTime(String updateTime) {
			this.updateTime = updateTime;
			return this;
		}

		public CurrencyDTO build() {
			return new CurrencyDTO(this);
		}
	}
}
