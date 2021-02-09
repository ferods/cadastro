package com.feros.contatos.cadastro.requisicoes;

import com.feros.contatos.cadastro.models.Endereco;
import com.feros.contatos.cadastro.models.Uf;

public class RequisicaoEndereco {
	
	private String cepCadastro;
	private String logradouroCadastro;
	private String numeroCadastro;
	private String complementoCadastro;
	private String bairroCadastro;
	private String cidadeCadastro;
	private String ufCadastro;
	private Long cpfPessoa;
	
	
	public String getLogradouroCadastro() {
		return logradouroCadastro;
	}
	public void setLogradouroCadastro(String logradouroCadastro) {
		this.logradouroCadastro = logradouroCadastro;
	}
	public String getNumeroCadastro() {
		return numeroCadastro;
	}
	public void setNumeroCadastro(String numeroCadastro) {
		this.numeroCadastro = numeroCadastro;
	}
	public String getComplementoCadastro() {
		return complementoCadastro;
	}
	public void setComplementoCadastro(String complementoCadastro) {
		this.complementoCadastro = complementoCadastro;
	}
	public String getBairroCadastro() {
		return bairroCadastro;
	}
	public void setBairroCadastro(String bairroCadastro) {
		this.bairroCadastro = bairroCadastro;
	}
	public String getCepCadastro() {
		return cepCadastro;
	}
	public void setCepCadastro(String cepCadastro) {
		this.cepCadastro = cepCadastro;
	}
	public String getCidadeCadastro() {
		return cidadeCadastro;
	}
	public void setCidadeCadastro(String cidadeCadastro) {
		this.cidadeCadastro = cidadeCadastro;
	}
	public String getUfCadastro() {
		return ufCadastro;
	}
	public void setUfCadastro(String ufCadastro) {
		this.ufCadastro = ufCadastro;
	}
	
	public Long getCpfPessoa() {
		return cpfPessoa;
	}
	public void setCpfPessoa(Long cpfPessoa) {
		this.cpfPessoa = cpfPessoa;
	}
	
	
	
	public Endereco toEndereco() {
		
		Endereco endereco = new Endereco();
		endereco.setCep(this.cepCadastro);
		endereco.setLogradouro(this.logradouroCadastro);
		endereco.setNumero(this.numeroCadastro);
		endereco.setComplemento(this.complementoCadastro);
		endereco.setBairro(this.bairroCadastro);
		endereco.setCidade(this.cidadeCadastro);
		endereco.setUf(Uf.valueOf(this.ufCadastro.toUpperCase()));
		
		return endereco;
	}
	
	

}
