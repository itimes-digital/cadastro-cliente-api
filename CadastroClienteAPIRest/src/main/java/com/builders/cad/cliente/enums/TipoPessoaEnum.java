package com.builders.cad.cliente.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoPessoaEnum {

	PESSOA_FISICA("F"),
	PESSOA_JURIDICA("J");
	
	private String tipoPessoa;
	
	private static final Map<String, TipoPessoaEnum> _map = new HashMap<String, TipoPessoaEnum>();
    
	static {
        for (TipoPessoaEnum tipoPessoaEnum : TipoPessoaEnum.values())
            _map.put(tipoPessoaEnum.tipoPessoa, tipoPessoaEnum);
    }
	
	
	public TipoPessoaEnum getStatus(String tipoPessoa) {
		
		return _map.get(tipoPessoa);
	}

}
