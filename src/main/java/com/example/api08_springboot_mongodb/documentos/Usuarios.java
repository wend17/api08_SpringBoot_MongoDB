package com.example.api08_springboot_mongodb.documentos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "Usuarios")
public class Usuarios {
    @Id
    private String id;
    private String nombre;
    private String email;
}
