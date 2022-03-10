package com.builders.cad.cliente.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoContatoEnum {

	CONTATO_RESIDENCIAL(1),
	CONTATO_COMERCIAL(2);
	
	private Integer tipoContato;
	
	private static final Map<Integer, TipoContatoEnum> _map = new HashMap<Integer, TipoContatoEnum>();
    
	static {
        for (TipoContatoEnum tipoContatoEnum : TipoContatoEnum.values())
            _map.put(tipoContatoEnum.tipoContato, tipoContatoEnum);
    }
	
	
	public TipoContatoEnum getStatus(Integer tipoContato) {
		
		return _map.get(tipoContato);
	}
	
}
