package br.edu.utfpr.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.api.dto.GatewayDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Gateway;
import br.edu.utfpr.api.service.GatewayService;
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
@RequestMapping("/gateway")
public class GatewayController {
    @Autowired
        private GatewayService gatewayService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id){
        var gateway = gatewayService.getById(id);
    
        return gateway.isPresent() ? ResponseEntity.ok().body(gateway.get())
        : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Gateway>> getAll() {
        List<Gateway> gateways = gatewayService.getAll();
        return ResponseEntity.ok(gateways);
    }

    @PostMapping    
    public ResponseEntity<Object> create(@RequestBody GatewayDTO dto){
        try{
            var res = gatewayService.create(dto);

            //seta o status para 201 (CREATED)  e retorna o objeto Pessoa em JSON 
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }catch(Exception ex){
            // seta o status para 400 (bad request) e devolve a mensagem da exceção lançada.
            return ResponseEntity.badRequest().body(ex.getMessage());       
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody GatewayDTO dto){
        try {
            return ResponseEntity.ok().body(gatewayService.update(id, dto));
        } catch (NoteFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception ex){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        try {
            gatewayService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NoteFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Gateway>> getByUserId(@PathVariable("userId") long userId) {
        List<Gateway> gateways = gatewayService.findGatewayByPessoaid(userId);
        return ResponseEntity.ok(gateways);
    }
}
