package br.edu.utfpr.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.api.dto.AtuadorDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Atuador;
import br.edu.utfpr.api.repository.AtuadorRepository;
import br.edu.utfpr.api.repository.DispositivoRepository;
import jakarta.transaction.Transactional;

@Service
public class AtuadorService {

    @Autowired
    private AtuadorRepository atuadorRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Transactional
    public Atuador create(AtuadorDTO dto) throws NoteFoundException {
        var atuador = new Atuador();
        BeanUtils.copyProperties(dto, atuador);

        var dispositivo = dispositivoRepository.findById(dto.dispositivoid());
        if(dispositivo.isPresent())
            atuador.setDispositivo(dispositivo.get());
        else
            throw new NoteFoundException("Dispositivo não existe");

        return atuadorRepository.save(atuador);
    }

    public List<Atuador> getAll() {
        return atuadorRepository.findAll();
    }

    public Optional<Atuador> getById(long id) {
        return atuadorRepository.findById(id);
    }

    @Transactional
    public Atuador update(long id, AtuadorDTO dto) throws NoteFoundException{
        var res = atuadorRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Atuador " + id + " não existe");
        }

        var atuador = res.get();
        atuador.setNome(dto.nome());

        return atuadorRepository.save(atuador);
    }

    @Transactional
    public void delete(long id) throws NoteFoundException {
        var res = atuadorRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Atuador " + id + " não existe");
        }
        Atuador atuador = res.get();
        
        if (atuador.getDispositivo() != null) {
            atuador.getDispositivo().getAtuadores().remove(atuador);
        }

        atuadorRepository.delete(atuador);
    }

    public List<Atuador> findAtuadorByDispositivoid(long dispositivoid){
        return atuadorRepository.findByDispositivoDispositivoid(dispositivoid);
    }

}
