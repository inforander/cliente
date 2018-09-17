package com.sherwinwilliams.cliente.model;

public enum StatusCliente {

	INATIVO("Inativo"), ATIVO("Ativo");

	private String descricao;

	StatusCliente(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
