package br.edu.utfpr.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.api.dto.DispositivoDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Dispositivo;
import br.edu.utfpr.api.service.DispositivoService;

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
@RequestMapping("/dispositivo")
public class DispositivoController {
    @Autowired
        private DispositivoService dispositivoService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> GetById(@PathVariable("id") long id) {
        var dispositivo = dispositivoService.getById(id);
    
        return dispositivo.isPresent()
            ? ResponseEntity.ok().body(dispositivo.get())
            : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Dispositivo> getAll(){
        return dispositivoService.getAll();
    }

    @PostMapping    
    public ResponseEntity<Object> create(@RequestBody DispositivoDTO dto){
        try{
            var res = dispositivoService.create(dto);

            //seta o status para 201 (CREATED)  e retorna o objeto Pessoa em JSON 
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }catch(Exception ex){
            // seta o status para 400 (bad request) e devolve a mensagem da exceção lançada.
            return ResponseEntity.badRequest().body(ex.getMessage());       
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody DispositivoDTO dto){
        try {
            return ResponseEntity.ok().body(dispositivoService.update(id, dto));
        } catch (NoteFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception ex){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        try {
            dispositivoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NoteFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Dispositivo>> getDevicesByUserId(@PathVariable Long userId) {
        List<Dispositivo> dispositivos = dispositivoService.findByUserId(userId);
        return ResponseEntity.ok(dispositivos);
    }
}
