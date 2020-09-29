package com.feros.contatos.cadastro.model;

import javax.persistence.Embeddable;

import com.feros.contatos.cadastro.formularios.TelefoneForm;

@Embeddable
public class Telefone {

	private String residencial;
	private String comercial;
	private String celular;

	public Telefone(String residencial, String comercial, String celular) {
		this.residencial = residencial;
		this.comercial = comercial;
		this.celular = celular;
	}
	
	public Telefone(TelefoneForm telefoneForm) {
		this.residencial = telefoneForm.getResidencial();
		this.comercial = telefoneForm.getComercial();
		this.celular = telefoneForm.getCelular();
		
	}

	public Telefone() {

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

}