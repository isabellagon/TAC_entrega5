package br.edu.utfpr.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.utfpr.api.model.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long>{
    List<Dispositivo> findByGatewayGatewayid(long gatewayid);

    @Query("SELECT d FROM Dispositivo d WHERE d.gateway.pessoa.pessoaid = :userId")
    List<Dispositivo> findByGatewayPessoaid(@Param("userId") Long userId);
}
