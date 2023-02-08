package com.example.api08_springboot_mongodb.controller;

import com.example.api08_springboot_mongodb.documentos.Usuarios;
import com.example.api08_springboot_mongodb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> saveUsuario(@RequestBody Usuarios usuarios) {
        try {
            var uuid = UUID.randomUUID();
            usuarios.setId(uuid.toString());
            Usuarios usersave = usuarioRepository.save(usuarios);
            return new ResponseEntity<Usuarios>(usersave,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> findAllUsuarios() {
        try {
            List<Usuarios>usuarios=usuarioRepository.findAll();
            return new ResponseEntity<List<Usuarios>>(usuarios,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUsuario(@RequestBody Usuarios usuario) {
        try {
            usuarioRepository.findById(usuario.getId()).orElseThrow(
            ()->new RuntimeException("No existe usuario")); //tener en cuenta que se cae feo cuando no está el id.
            Usuarios usersave = usuarioRepository.save(usuario); // se guarda el actualizado,revisar el parametro ingresado
            return new ResponseEntity<Usuarios>(usersave,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") String id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<String>("Eliminado",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
