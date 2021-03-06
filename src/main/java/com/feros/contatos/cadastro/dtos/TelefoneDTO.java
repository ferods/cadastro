package com.feros.contatos.cadastro.dtos;

import com.feros.contatos.cadastro.models.Telefone;

public class TelefoneDTO {
	
	private String residencial;
	private String celular;


	public TelefoneDTO(Telefone telefone) {
		this.residencial = telefone.getResidencial();
		this.celular = telefone.getCelular();
	}

	public String getResidencial() {
		return residencial;
	}

	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}



}
