package com.tpi.banking.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpi.banking.BaseTest;
import com.tpi.banking.model.currency.CurrencyDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@AutoConfigureMockMvc
class CurrencyControllerTest extends BaseTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void testAddNewCurrency() throws Exception {

		CurrencyDTO actualCurrencyDTO = objectMapper.readValue(mockMvc
				.perform(post("/currency/add").contentType("application/json")
						.content(objectMapper.writeValueAsString(mockCurrencyDTO)))
				.andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(),
				CurrencyDTO.class);

		assertEquals(mockCurrencyDTO.getName(), actualCurrencyDTO.getName());
	}

	@Test
	void testGetAllCurrencies() throws Exception {

		testAddNewCurrency();

		String responseString = mockMvc.perform(get("/currency/all").contentType("application/json")).andDo(print())
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		List<CurrencyDTO> actualCurrencyDTOs = Arrays
				.asList(objectMapper.readValue(responseString, CurrencyDTO[].class));
		assertTrue(actualCurrencyDTOs.size() > 0);
		assertEquals(1,
				actualCurrencyDTOs.stream().filter(item -> item.getCode().equals(mockCurrencyDTO.getCode())).count());
	}

	@Test
	void testGetExistedCurrencyByCode() throws Exception {
		testAddNewCurrency();
		String responseString = mockMvc
				.perform(get("/currency/" + mockCurrencyDTO.getCode()).contentType("application/json")).andDo(print())
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		CurrencyDTO actualCurrencyDTO = objectMapper.readValue(responseString, CurrencyDTO.class);
		assertEquals(mockCurrencyDTO.getName(), actualCurrencyDTO.getName());
	}

	@Test
	void testGetNonExistedCurrencyByCode() throws Exception {
		mockMvc.perform(get("/currency/" + mockCurrencyDTO.getCode()).contentType("application/json")).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void testUpdateCurrency() throws Exception {
		testAddNewCurrency();
		mockCurrencyDTO.setName("Updated Name");
		String responseString = mockMvc
				.perform(put("/currency/update").contentType("application/json")
						.content(objectMapper.writeValueAsString(mockCurrencyDTO)))
				.andDo(print())
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		CurrencyDTO actualCurrencyDTO = objectMapper.readValue(responseString, CurrencyDTO.class);
		assertEquals(mockCurrencyDTO.getName(), actualCurrencyDTO.getName());
	}

	@Test
	void testDeleteCurrency() throws Exception {
		testAddNewCurrency();
		mockMvc.perform(delete("/currency/" + mockCurrencyDTO.getCode()).contentType("application/json")).andDo(print())
				.andExpect(status().isOk());
		testGetNonExistedCurrencyByCode();
	}
}
