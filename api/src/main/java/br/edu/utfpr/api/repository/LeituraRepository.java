package br.edu.utfpr.api.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.api.model.Leitura;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    List<Leitura> findBySensorSensorid(long sensorid);
}
