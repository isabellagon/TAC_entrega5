package br.edu.utfpr.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.api.dto.GatewayDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Gateway;
import br.edu.utfpr.api.repository.GatewayRepository;
import br.edu.utfpr.api.repository.PessoaRepository;

@Service
public class GatewayService {

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired  
    private PessoaRepository pessoaRepository;

    public Gateway create(GatewayDTO dto) throws NoteFoundException {
        var gateway = new Gateway();
        BeanUtils.copyProperties(dto, gateway);

        var pessoa = pessoaRepository.findById(dto.pessoaid());
        if(pessoa.isPresent())
            gateway.setPessoa(pessoa.get());
        else
            throw new NoteFoundException("Pessoa não existe");

        // Persistir no DB
        return gatewayRepository.save(gateway);
    }

    public List<Gateway> getAll() {
        return gatewayRepository.findAll();
    }

    public Optional<Gateway> getById(long id) {
        return gatewayRepository.findById(id);
    }

    public Gateway update(long id, GatewayDTO dto) throws NoteFoundException{
        var res = gatewayRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Gateway " + id + " não existe");
        }

        var gateway = res.get();
        gateway.setNome(dto.nome());
        gateway.setDescricao(dto.descricao());

        return gatewayRepository.save(gateway);
    }

    public void delete(long id) throws NoteFoundException {
        var res = gatewayRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Gateway " + id + " não existe");
        }
        gatewayRepository.delete(res.get());
    }

    public List<Gateway> findGatewayByPessoaid(long pessoaid){
        return gatewayRepository.findByPessoaPessoaid(pessoaid);
    }
}
