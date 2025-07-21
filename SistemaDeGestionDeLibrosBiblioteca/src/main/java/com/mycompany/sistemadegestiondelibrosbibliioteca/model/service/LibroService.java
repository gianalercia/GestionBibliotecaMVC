/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadegestiondelibrosbibliioteca.model.service;

/**
 *
 * @author gian_
 */
import com.mycompany.sistemadegestiondelibrosbibliioteca.model.dao.LibroDAO;
import com.mycompany.sistemadegestiondelibrosbibliioteca.model.dto.LibroDTO;
import com.mycompany.sistemadegestiondelibrosbibliioteca.model.entity.Libro;
import java.util.Optional;

/**
 * LibroService - Capa de lógica de negocio
 * Contiene validaciones y reglas de negocio
 * Convierte entre Entity y DTO
 */
public class LibroService {
    private LibroDAO libroDAO;
    
    /**
     * Constructor
     */
    public LibroService() {
        this.libroDAO = new LibroDAO();
    }
    
    /**
     * Obtener libro por ID con validaciones de negocio
     * @param id ID del libro a buscar
     * @return LibroDTO con los datos del libro (sin campo disponible)
     * @throws IllegalArgumentException si el ID es inválido
     * @throws RuntimeException si el libro no existe (404)
     */
    public LibroDTO obtenerLibroPorId(Long id) {
        // Validación de negocio: ID no puede ser nulo
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo (Error 400)");
        }
        
        // Validación de negocio: ID debe ser positivo
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo (Error 400)");
        }
        
        // Buscar en la base de datos
        Optional<Libro> libroOpt = libroDAO.findById(id);
        
        // Manejo de error 404: libro no encontrado
        if (!libroOpt.isPresent()) {
            throw new RuntimeException("Libro no encontrado con ID: " + id + " (Error 404)");
        }
        
        Libro libro = libroOpt.get();
        
        // Convertir Entity a DTO (ocultar campo disponible)
        return convertirADTO(libro);
    }
    
    /**
     * Agregar nuevo libro con validaciones de negocio
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param anoPublicacion Año de publicación
     * @return LibroDTO del libro creado
     * @throws IllegalArgumentException si los datos son inválidos (400)
     */
    public LibroDTO agregarLibro(String titulo, String autor, Integer anoPublicacion) {
        // Ejecutar todas las validaciones de negocio
        validarDatosLibro(titulo, autor, anoPublicacion);
        
        // Crear nueva entidad
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(titulo.trim());
        nuevoLibro.setAutor(autor.trim());
        nuevoLibro.setAnoPublicacion(anoPublicacion);
        nuevoLibro.setDisponible(true); // Por defecto disponible
        
        // Guardar en la base de datos
        Libro libroGuardado = libroDAO.save(nuevoLibro);
        
        // Convertir a DTO para retornar
        return convertirADTO(libroGuardado);
    }
    
    /**
     * Validaciones completas de datos de negocio
     * @param titulo Título a validar
     * @param autor Autor a validar
     * @param anoPublicacion Año a validar
     * @throws IllegalArgumentException si algún dato es inválido (Error 400)
     */
    private void validarDatosLibro(String titulo, String autor, Integer anoPublicacion) {
        // Validar título
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío (Error 400)");
        }
        
        if (titulo.trim().length() < 2) {
            throw new IllegalArgumentException("El título debe tener al menos 2 caracteres (Error 400)");
        }
        
        if (titulo.trim().length() > 200) {
            throw new IllegalArgumentException("El título no puede exceder 200 caracteres (Error 400)");
        }
        
        // Validar autor
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío (Error 400)");
        }
        
        if (autor.trim().length() < 2) {
            throw new IllegalArgumentException("El autor debe tener al menos 2 caracteres (Error 400)");
        }
        
        if (autor.trim().length() > 100) {
            throw new IllegalArgumentException("El autor no puede exceder 100 caracteres (Error 400)");
        }
        
        // Validar año de publicación
        if (anoPublicacion == null) {
            throw new IllegalArgumentException("El año de publicación no puede ser nulo (Error 400)");
        }
        
        if (anoPublicacion <= 0) {
            throw new IllegalArgumentException("El año de publicación debe ser válido (Error 400)");
        }
        
        // Validar que no sea un año futuro
        int anoActual = java.time.Year.now().getValue();
        if (anoPublicacion > anoActual) {
            throw new IllegalArgumentException("El año de publicación no puede ser futuro (Error 400)");
        }
        
        // Validar rango razonable (libros desde año 1000)
        if (anoPublicacion < 1000) {
            throw new IllegalArgumentException("El año de publicación debe ser desde el año 1000 (Error 400)");
        }
    }
    
    /**
     * Conversión de Entity a DTO
     * IMPORTANTE: Oculta el campo 'disponible'
     * @param libro Entity a convertir
     * @return LibroDTO sin campo disponible
     */
    private LibroDTO convertirADTO(Libro libro) {
        return new LibroDTO(
            libro.getId(),
            libro.getTitulo(),
            libro.getAutor(),
            libro.getAnoPublicacion()
            // Nota: NO se incluye el campo 'disponible'
        );
    }
    
    /**
     * Método auxiliar para obtener estadísticas (para testing)
     * @return número total de libros
     */
    public int contarLibros() {
        return libroDAO.count();
    }
}