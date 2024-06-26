package br.edu.utfpr.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.api.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByEmail(String email);

    Optional<Pessoa> findByNome(String nome);
}
