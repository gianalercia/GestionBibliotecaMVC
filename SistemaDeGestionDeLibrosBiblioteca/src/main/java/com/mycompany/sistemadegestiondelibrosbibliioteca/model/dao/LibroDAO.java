/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadegestiondelibrosbibliioteca.model.dao;

/**
 *
 * Lo que hace el DAO es separar la logica de negocio de la logica de acceso a datos.
 */
import com.mycompany.sistemadegestiondelibrosbibliioteca.model.entity.Libro;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * LibroDAO - Data Access Object
 */
public class LibroDAO {
    // Base de datos simulada con HashMap en memoria (al ser en memoria, es volatil)
    private static Map<Long, Libro> baseDatos = new HashMap<>();
    // Atributo ID para simular un ID autoincremental en nuestra base
    private static Long nextId = 1L;
    
    // Bloque estático para inicializar datos de prueba
    static {
        baseDatos.put(1L, new Libro(1L, "Danza de Dragones", "George R. R. Martin", 2011, true));
        baseDatos.put(2L, new Libro(2L, "Cien años de soledad", "Gabriel García Márquez", 1967, false));
        baseDatos.put(3L, new Libro(3L, "It", "Stephen King", 1986, true));
        baseDatos.put(4L, new Libro(4L, "El Principito", "Antoine de Saint-Exupéry", 1943, true));
        baseDatos.put(5L, new Libro(5L, "Alicia en el País de las Maravillas", "Lewis Carrol", 1865, false));
        nextId = 6L;
    }
    
    /**
     * Metodo de buscar libro por ID
     */
    public Optional<Libro> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(baseDatos.get(id));
    }
    
    /**
     * metodo de guardar libro (crear o actualizar)
     */
    public Libro save(Libro libro) {
        if (libro.getId() == null) {
            // Crear nuevo libro - asignar ID automáticamente
            libro.setId(nextId++);
        }
        // Guardar en la "base de datos"
        baseDatos.put(libro.getId(), libro);
        return libro;
    }
    
    /**
     * Metodo para obtener todos los libros
     */
    public Map<Long, Libro> findAll() {
        return new HashMap<>(baseDatos);
    }

}