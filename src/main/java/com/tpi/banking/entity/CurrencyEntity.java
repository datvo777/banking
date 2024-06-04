package com.tpi.banking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "currency")
@Entity
public class CurrencyEntity {

	@Id
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
	
}
