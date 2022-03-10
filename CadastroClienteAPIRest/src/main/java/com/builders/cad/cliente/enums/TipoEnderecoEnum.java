package com.builders.cad.cliente.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoEnderecoEnum {

	ENDERECO_RESIDENCIAL(1),
	ENDERECO_COMERCIAL(2);
	
	private Integer tipoEndereco;
	
	private static final Map<Integer, TipoEnderecoEnum> _map = new HashMap<Integer, TipoEnderecoEnum>();
    
	static {
        for (TipoEnderecoEnum tipoEnderecoEnum : TipoEnderecoEnum.values())
            _map.put(tipoEnderecoEnum.tipoEndereco, tipoEnderecoEnum);
    }
	
	
	public TipoEnderecoEnum getStatus(Integer tipoEndereco) {
		
		return _map.get(tipoEndereco);
	}
	
}
