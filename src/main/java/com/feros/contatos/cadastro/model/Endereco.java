package com.feros.contatos.cadastro.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.feros.contatos.cadastro.formularios.EnderecoForm;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private Integer cep;
	private String cidade;
	@Enumerated(EnumType.STRING)
	private Uf uf = Uf.SP;
	@Column(unique = true)
	private Long codigoSecundario;
	@ManyToMany(mappedBy = "enderecos")
	private List<Pessoa> pessoas;

	public Endereco(String logradouro, Integer numero, String complemento, String bairro, Integer cep, String cidade,
			Uf uf, Long codigoSecundario) {

		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.codigoSecundario = codigoSecundario;

	}

	public Endereco(EnderecoForm enderecoForm) {

		this.logradouro = enderecoForm.getLogradouro();
		this.numero = Integer.parseInt(enderecoForm.getNumero());
		this.complemento = enderecoForm.getComplemento();
		this.bairro = enderecoForm.getBairro();
		this.cep = Integer.parseInt(enderecoForm.getCep());
		this.cidade = enderecoForm.getCidade();
		this.uf = enderecoForm.getUf();
		this.codigoSecundario = enderecoForm.converterStringParaNumero(enderecoForm.getCep(), enderecoForm.getNumero());
	
	}

	public Endereco() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Long getCodigoSecundario() {
		return codigoSecundario;
	}

	public void setCodigoSecundario(Long codigoSecundario) {
		this.codigoSecundario = codigoSecundario;
	}

	

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento="
				+ complemento + ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", uf=" + uf
				+ ", codigoSecundario=" + codigoSecundario + ", pessoaCpf=" + ", pessoas=" + pessoas + "]";
	}
	
	
	

}
