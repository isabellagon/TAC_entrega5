package br.edu.utfpr.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.utfpr.api.dto.PessoaDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

     @Autowired
    private PasswordEncoder passwordEncoder;

    public Pessoa create(PessoaDTO dto){
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(dto, pessoa);

        pessoa.setSenha(passwordEncoder.encode(dto.getSenha()));
        
        //Persistir no banco de dados
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> getAll(){
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getByid(long id){
        return pessoaRepository.findById(id);
    }

    public Optional<Pessoa> findByEmail(String email) {
        return pessoaRepository.findByEmail(email);
    }

    public Pessoa update(Long id, PessoaDTO dto) throws NoteFoundException{
        var res = pessoaRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Pessoa " + id + " não existe.");
        }

        var pessoa = res.get();
        pessoa.setNome((dto.nome()));
        pessoa.setEmail(dto.email());

        return pessoaRepository.save(pessoa);
        
    }

    public void delete(long id) throws NoteFoundException{
        var res = pessoaRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Pessoa " + id + " não existe.");
        }

        pessoaRepository.delete(res.get());

    }

}
