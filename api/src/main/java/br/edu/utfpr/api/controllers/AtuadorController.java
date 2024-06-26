package br.edu.utfpr.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.api.dto.AtuadorDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Atuador;
import br.edu.utfpr.api.service.AtuadorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/atuador")
public class AtuadorController {
    @Autowired
    private AtuadorService atuadorService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> GetById(@PathVariable("id") long id) {
       var atuador = atuadorService.getById(id);

       return atuador.isPresent()
            ? ResponseEntity.ok().body(atuador.get())
            : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Atuador> getAll() {
        return atuadorService.getAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AtuadorDTO dto) {
        try {
            var res = atuadorService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") long id, @RequestBody AtuadorDTO dto) {
        try {
            return ResponseEntity.ok().body(atuadorService.update(id, dto));
        }catch(NoteFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        try {
            atuadorService.delete(id);
            return ResponseEntity.ok().build();
        } catch(NoteFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

