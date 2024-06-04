package com.tpi.banking;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tpi.banking.model.currency.CurrencyDTO;

@SpringBootTest
@Transactional
public class BaseTest {
	public static CurrencyDTO mockCurrencyDTO;
	
	@BeforeAll
	public static  void setup () {
		mockCurrencyDTO = new CurrencyDTO.Builder()
                .code("VND")
                .name("Viet Nam Dong")
                .build();
	}

}
