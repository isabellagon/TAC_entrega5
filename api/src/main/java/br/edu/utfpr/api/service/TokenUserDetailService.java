package br.edu.utfpr.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.repository.PessoaRepository;

@Component
public class TokenUserDetailService implements UserDetailsService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new CustomUserDetails(pessoa.getEmail(), pessoa.getSenha(), pessoa.getPessoaid());
    }
}
