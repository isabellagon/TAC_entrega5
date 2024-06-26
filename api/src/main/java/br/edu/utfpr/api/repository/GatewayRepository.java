package br.edu.utfpr.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.api.model.Gateway;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    List<Gateway> findByPessoaPessoaid(long pessoaid);
}
