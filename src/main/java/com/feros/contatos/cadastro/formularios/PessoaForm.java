package com.feros.contatos.cadastro.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.feros.contatos.cadastro.model.Endereco;
import com.feros.contatos.cadastro.model.Pessoa;
import com.feros.contatos.cadastro.model.Telefone;
import com.feros.contatos.cadastro.repositories.EnderecoRepository;

public class PessoaForm {

	@NotNull @NotEmpty @Length(max = 50)
	private String nome;
	@NotNull
	private Long cpf;
	@NotNull @NotEmpty @Length(max = 35)
	private String email;
	private List<EnderecoForm> enderecosForm;
	private TelefoneForm telefones;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<EnderecoForm> getEnderecosForm() {
		return enderecosForm;
	}

	public void setEnderecosForm(List<EnderecoForm> enderecosForm) {
		this.enderecosForm = enderecosForm;
	}

	public TelefoneForm getTelefones() {
		return telefones;
	}

	public void setTelefones(TelefoneForm telefones) {
		this.telefones = telefones;
	}
	
	
	

	public Pessoa converter(EnderecoRepository enderecoRepository, List<Endereco> enderecos) {
		Telefone telefone = new Telefone(this.telefones);
		
		List<Endereco> listaDeEnderecosDoRep = new ArrayList<Endereco>();
		
		for (Endereco endereco : enderecos) {
			List<Endereco> enderecosRep = enderecoRepository.findByCodigoSecundario(endereco.getCodigoSecundario());			
			listaDeEnderecosDoRep.addAll(enderecosRep);
		}
		
		return new Pessoa(this.nome, this.cpf, this.email, telefone, listaDeEnderecosDoRep);
		
		
		
	}



}
