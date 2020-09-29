package com.feros.contatos.cadastro.dtos;

import com.feros.contatos.cadastro.model.Pessoa;
import com.feros.contatos.cadastro.model.Telefone;

public class TelefoneDTO {
	
	private String residencial;
	private String comercial;
	private String celular;

	public TelefoneDTO(String residencial, String comercial, String celular) {
		this.residencial = residencial;
		this.comercial = comercial;
		this.celular = celular;
	}

	public TelefoneDTO(Telefone telefone) {
		this.residencial = telefone.getResidencial();
		this.comercial = telefone.getComercial();
		this.celular = telefone.getCelular();
	}

	public String getResidencial() {
		return residencial;
	}

	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public static TelefoneDTO converter(Pessoa pessoa) {
		return new TelefoneDTO(pessoa.getTelefone().getResidencial(), pessoa.getTelefone().getComercial(),
				pessoa.getTelefone().getCelular());
	}
	
	

}
