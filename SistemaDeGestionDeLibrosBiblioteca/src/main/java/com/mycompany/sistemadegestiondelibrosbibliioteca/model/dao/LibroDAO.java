/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadegestiondelibrosbibliioteca.model.dao;

/**
 *
 * @author gian_
 */
import com.mycompany.sistemadegestiondelibrosbibliioteca.model.entity.Libro;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * LibroDAO - Data Access Object
 * Implementa acceso a datos con métodos findById() y save()
 * Base de datos simulada con HashMap en memoria
 */
public class LibroDAO {
    // Base de datos simulada con mapa en memoria
    private static Map<Long, Libro> baseDatos = new HashMap<>();
    private static Long nextId = 1L;
    
    // Bloque estático para inicializar datos de prueba
    static {
        baseDatos.put(1L, new Libro(1L, "El Quijote", "Miguel de Cervantes", 1605, true));
        baseDatos.put(2L, new Libro(2L, "Cien años de soledad", "Gabriel García Márquez", 1967, false));
        baseDatos.put(3L, new Libro(3L, "1984", "George Orwell", 1949, true));
        baseDatos.put(4L, new Libro(4L, "El Principito", "Antoine de Saint-Exupéry", 1943, true));
        baseDatos.put(5L, new Libro(5L, "Crimen y Castigo", "Fiódor Dostoyevski", 1866, false));
        nextId = 6L;
    }
    
    /**
     * Buscar libro por ID
     * @param id ID del libro a buscar
     * @return Optional<Libro> - libro encontrado o vacío si no existe
     */
    public Optional<Libro> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(baseDatos.get(id));
    }
    
    /**
     * Guardar libro (crear o actualizar)
     * @param libro Libro a guardar
     * @return Libro guardado con ID asignado
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
     * Obtener todos los libros (método auxiliar para testing)
     * @return Map con todos los libros
     */
    public Map<Long, Libro> findAll() {
        return new HashMap<>(baseDatos);
    }
    
    /**
     * Verificar si existe un libro con el ID dado
     * @param id ID a verificar
     * @return true si existe, false si no
     */
    public boolean existsById(Long id) {
        return id != null && baseDatos.containsKey(id);
    }
    
    /**
     * Contar total de libros
     * @return número total de libros
     */
    public int count() {
        return baseDatos.size();
    }
}