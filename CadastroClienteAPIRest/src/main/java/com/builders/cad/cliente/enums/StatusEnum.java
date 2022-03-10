package com.builders.cad.cliente.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum StatusEnum {

	ATIVO(1),
	INATIVO(2);
	
	private Integer tipoStatusAtivo;
	
	StatusEnum(Integer tipoStatusAtivo){
		this.tipoStatusAtivo = tipoStatusAtivo;
	}
	
	private static final Map<Integer, StatusEnum> _map = new HashMap<Integer, StatusEnum>();
    
	static {
        for (StatusEnum statusEnum : StatusEnum.values())
            _map.put(statusEnum.tipoStatusAtivo, statusEnum);
    }
	
	
	public StatusEnum getStatus(Integer tipoStatusAtivo) {
		
		return _map.get(tipoStatusAtivo);
	}

}
