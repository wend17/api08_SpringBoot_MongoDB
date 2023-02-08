package com.example.api08_springboot_mongodb.repository;

import com.example.api08_springboot_mongodb.documentos.Usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuarios,String> {
}
