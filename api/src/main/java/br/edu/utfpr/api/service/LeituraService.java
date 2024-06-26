package br.edu.utfpr.api.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.api.dto.LeituraDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Leitura;
import br.edu.utfpr.api.repository.LeituraRepository;
import br.edu.utfpr.api.repository.SensorRepository;


@Service
public class LeituraService {
    @Autowired
    private LeituraRepository leituraRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public Leitura create(LeituraDTO dto) throws NoteFoundException{
        var leitura = new Leitura();
        BeanUtils.copyProperties(dto, leitura);

        var sensor = sensorRepository.findById(dto.sensorid()); 
        if (sensor.isPresent()) {
            leitura.setSensor(sensor.get());
        } else {
            throw new NoteFoundException("Sensor não existe");
        }

        // Definir a data e hora atual
        leitura.setDataHora(LocalDateTime.now());
                
        //Persistir no banco de dados
        return leituraRepository.save(leitura);
    }

    public List<Leitura> getAll(){
        return leituraRepository.findAll();
    }

    public Optional<Leitura> getByid(long id){
        return leituraRepository.findById(id);
    }

    public Leitura update(Long id, LeituraDTO dto) throws NoteFoundException{
        var res = leituraRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Leitura " + id + " não existe.");
        }

        var leitura = res.get();
        leitura.setValor((dto.valor()));
        var sensor = sensorRepository.findById(dto.sensorid()); 
        if (sensor.isPresent()) {
            leitura.setSensor(sensor.get());
        } else {
            throw new NoteFoundException("Pessoa não existe");
        }

        return leituraRepository.save(leitura);
        
    }

    public void delete(long id) throws NoteFoundException{
        var res = leituraRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Leitura " + id + " não existe.");
        }

        leituraRepository.delete(res.get());
    }

    public List<Leitura> findLeituraBySensorid(long sensorid){
        return leituraRepository.findBySensorSensorid(sensorid);
    }
}
