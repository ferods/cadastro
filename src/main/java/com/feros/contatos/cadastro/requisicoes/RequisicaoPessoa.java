package com.feros.contatos.cadastro.requisicoes;

import java.util.List;
import java.util.stream.Collectors;

import com.feros.contatos.cadastro.models.Endereco;
import com.feros.contatos.cadastro.models.Pessoa;
import com.feros.contatos.cadastro.models.Telefone;

public class RequisicaoPessoa {

	private Long cpfCadastro;
	private String nomeCadastro;
	private String emailCadastro;
	private List<RequisicaoEndereco> enderecosCadastro;
	private RequisicaoTelefone telefoneCadastro;

	public Long getCpfCadastro() {
		return cpfCadastro;
	}

	public void setCpfCadastro(Long cpfCadastro) {
		this.cpfCadastro = cpfCadastro;
	}

	public String getNomeCadastro() {
		return nomeCadastro;
	}

	public void setNomeCadastro(String nomeCadastro) {
		this.nomeCadastro = nomeCadastro;
	}

	public String getEmailCadastro() {
		return emailCadastro;
	}

	public void setEmailCadastro(String emailCadastro) {
		this.emailCadastro = emailCadastro;
	}

	public List<RequisicaoEndereco> getEnderecosCadastro() {
		return enderecosCadastro;
	}

	public void setEnderecosCadastro(List<RequisicaoEndereco> enderecosCadastro) {
		this.enderecosCadastro = enderecosCadastro;
	}

	public RequisicaoTelefone getTelefoneCadastro() {
		return telefoneCadastro;
	}

	public void setTelefoneCadastro(RequisicaoTelefone telefoneCadastro) {
		this.telefoneCadastro = telefoneCadastro;
	}
	

	public Pessoa toPessoa() {

		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(this.cpfCadastro);
		pessoa.setNome(this.nomeCadastro);
		pessoa.setEmail(this.emailCadastro);

		Telefone telefone = new Telefone();
		telefone.setResidencial(this.telefoneCadastro.getResidencialCadastro());
		telefone.setCelular(this.telefoneCadastro.getCelularCadastro());
		
		pessoa.setTelefone(telefone);
		
		List<Endereco> enderecos = this.enderecosCadastro.stream().map(RequisicaoEndereco :: toEndereco).collect(Collectors.toList());
		pessoa.addEnderecos(enderecos);
		
		for (Endereco endereco : enderecos) {
			endereco.setPessoa(pessoa);
		}
		
		return pessoa;
	}

}
