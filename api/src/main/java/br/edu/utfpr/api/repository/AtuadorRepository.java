package br.edu.utfpr.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.api.model.Atuador;

public interface AtuadorRepository extends JpaRepository<Atuador, Long> {
    List<Atuador> findByDispositivoDispositivoid(long dispositivoid);
}
