package com.feros.contatos.cadastro.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.feros.contatos.cadastro.model.Endereco;
import com.feros.contatos.cadastro.model.Pessoa;
import com.feros.contatos.cadastro.repositories.EnderecoRepository;
import com.feros.contatos.cadastro.repositories.PessoaRepository;

public class VinculoForm {
	
	@NotNull
	private Long idPessoa;

	public Long getIdPessoa() {
		return idPessoa;
	}
	
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	public Pessoa vincular(Long id, EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
		Endereco endereco = enderecoRepository.getOne(id);
		Pessoa pessoa = pessoaRepository.getOne(this.idPessoa);
		List<Endereco> listaDeEnderecos = new ArrayList<Endereco>();
		listaDeEnderecos.addAll(pessoa.getEnderecos());
		listaDeEnderecos.add(endereco);
		pessoa.setEnderecos(listaDeEnderecos);
		return pessoa;
	}
	
	

}
