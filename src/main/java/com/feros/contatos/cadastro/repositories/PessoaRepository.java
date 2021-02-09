package com.feros.contatos.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feros.contatos.cadastro.models.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>  {

	List<Pessoa> findByNome(String nome);

}
