package com.feros.contatos.cadastro.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.feros.contatos.cadastro.dtos.DetalhesPessoaEnderecosDTO;
import com.feros.contatos.cadastro.dtos.PessoaDTO;
import com.feros.contatos.cadastro.dtos.PessoaEnderecosDTO;
import com.feros.contatos.cadastro.formularios.AtualizacaoPessoaForm;
import com.feros.contatos.cadastro.formularios.EnderecoForm;
import com.feros.contatos.cadastro.formularios.PessoaForm;
import com.feros.contatos.cadastro.model.Endereco;
import com.feros.contatos.cadastro.model.Pessoa;
import com.feros.contatos.cadastro.repositories.EnderecoRepository;
import com.feros.contatos.cadastro.repositories.PessoaRepository;

@Service
@Transactional
public class PessoaService {
	
	
	

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	
	
	
	
	

	public ResponseEntity<PessoaEnderecosDTO> salvar(PessoaForm pessoaForm, UriComponentsBuilder uriComponentsBuilder) {

		List<Endereco> enderecos = EnderecoForm.converterEnderecosFormParaEnderecosMetodoSalvarPessoasService(pessoaForm.getEnderecosForm());

		if (existeCodigoSecundarioNoRepositorioDoEndereco(enderecos)) {
			List<Endereco> enderecosFiltrados = separarEnderecosQueNaoExistemNoRepositorio(enderecos);

			enderecoRepository.saveAll(enderecosFiltrados);

			Pessoa pessoa = pessoaForm.converter(enderecoRepository, enderecos);
			pessoaRepository.save(pessoa);

			URI uri = uriComponentsBuilder.path("cadastros/id").buildAndExpand(pessoa.getId()).toUri();
			return ResponseEntity.created(uri).body(new PessoaEnderecosDTO(pessoa));

		} else {

			enderecoRepository.saveAll(enderecos);

			Pessoa pessoa = pessoaForm.converter(enderecoRepository, enderecos);
			pessoaRepository.save(pessoa);

			URI uri = uriComponentsBuilder.path("cadastros/id").buildAndExpand(pessoa.getId()).toUri();
			return ResponseEntity.created(uri).body(new PessoaEnderecosDTO(pessoa));

		}


	}
	
	
	
	
	
	

	private List<Endereco> separarEnderecosQueNaoExistemNoRepositorio(List<Endereco> enderecos) {

		List<Endereco> listaDeEnderecosNaoExistentesNoRepositorio = new ArrayList<Endereco>();

		for (Endereco endereco : enderecos) {

			List<Endereco> enderecosDoRepositorio = enderecoRepository
					.findByCodigoSecundario(endereco.getCodigoSecundario());

			if (enderecosDoRepositorio.isEmpty()) {
				listaDeEnderecosNaoExistentesNoRepositorio.add(endereco);
			}
		}

		return listaDeEnderecosNaoExistentesNoRepositorio;
	}
	
	
	
	
	

	private boolean existeCodigoSecundarioNoRepositorioDoEndereco(List<Endereco> enderecos) {

		for (Endereco endereco : enderecos) {

			List<Endereco> enderecosDoRepositorio = enderecoRepository
					.findByCodigoSecundario(endereco.getCodigoSecundario());

			if (!enderecosDoRepositorio.isEmpty()) {
				return true;
			}
		}

		return false;
	}
	
	
	
	

	// listar somente pessoas
	public List<PessoaDTO> listarPessoas(String nome) {
		if (nome == null) {
			List<Pessoa> pessoas = pessoaRepository.findAll();
			return PessoaDTO.converter(pessoas);
		} else {
			List<Pessoa> pessoas = pessoaRepository.findByNome(nome);
			return PessoaDTO.converter(pessoas);
		}
	}
	
	
	
	

	// lista de pessoas com os seus enderecos
	public List<PessoaEnderecosDTO> listarPessoaEnderecos(String nome) {
		if (nome == null) {
			List<Pessoa> pessoas = pessoaRepository.findAll();
			return PessoaEnderecosDTO.converter(pessoas);
		}

		List<Pessoa> pessoas = pessoaRepository.findByNome(nome);
		return PessoaEnderecosDTO.converter(pessoas);

	}
	
	
	
	
	

	public ResponseEntity<PessoaDTO> atualizarPessoa(Long id, AtualizacaoPessoaForm form) {
		Pessoa pessoaAtualizada = form.atualizar(id, pessoaRepository);
		return ResponseEntity.ok(new PessoaDTO(pessoaAtualizada));
	}

	
	
	
	

	public ResponseEntity<?> deletar(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			pessoaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	
	
	
	

	public DetalhesPessoaEnderecosDTO detalharPessoaEnderecos(Long id) {
		Pessoa pessoa = pessoaRepository.getOne(id);
		return new DetalhesPessoaEnderecosDTO(pessoa);
	}
	
	
	

}
