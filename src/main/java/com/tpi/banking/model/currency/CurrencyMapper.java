package com.tpi.banking.model.currency;

import java.util.List;

import org.mapstruct.Mapper;

import com.tpi.banking.entity.CurrencyEntity;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
	
	CurrencyDTO entityToDTO(CurrencyEntity currencyEntity);
	List<CurrencyDTO> entitiesToDTOs(List<CurrencyEntity> currencyEntity);
	
	CurrencyEntity dtoToEntity(CurrencyDTO currencyDTO);
	List<CurrencyEntity> dtostoEntities(List<CurrencyDTO> currencyDTO);
}
