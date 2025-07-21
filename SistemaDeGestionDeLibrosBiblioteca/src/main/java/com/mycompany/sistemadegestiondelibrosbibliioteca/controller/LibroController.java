/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadegestiondelibrosbibliioteca.controller;

import com.mycompany.sistemadegestiondelibrosbibliioteca.model.service.LibroService;
import com.mycompany.sistemadegestiondelibrosbibliioteca.model.dto.LibroDTO;
import com.mycompany.sistemadegestiondelibrosbibliioteca.view.BibliotecaView;

/**
 * LibroController - Controlador MVC
 * Coordina entre Model (LibroService) y View (BibliotecaView)
 * Maneja endpoints REST y peticiones HTTP
 * 
 * @author gian_
 */
public class LibroController {
    private LibroService libroService;  // Referencia al MODEL
    private BibliotecaView view;         // Referencia a la VIEW
    
    /**
     * Constructor - Inicializa dependencias del MVC
     */
    public LibroController() {
        this.libroService = new LibroService();
        this.view = new BibliotecaView();
    }
    
    /**
     * Endpoint: GET /libros/{id}
     * Maneja la petición de búsqueda de libro por ID
     * Coordina Model y View sin contener lógica de negocio
     * @param id ID del libro a buscar
     */
    public void obtenerLibro(Long id) {
        try {
            // Coordina con el MODEL para obtener los datos
            LibroDTO libro = libroService.obtenerLibroPorId(id);
            
            // Coordina con la VIEW para mostrar la respuesta exitosa
            view.mostrarLibroEncontrado(libro);
            
        } catch (IllegalArgumentException e) {
            // Error 400 - Bad Request (datos inválidos)
            view.mostrarError(400, "Datos inválidos: " + e.getMessage());
            
        } catch (RuntimeException e) {
            if (e.getMessage().contains("404")) {
                // Error 404 - Not Found
                view.mostrarError(404, "Libro no encontrado");
            } else {
                // Error 500 - Internal Server Error
                view.mostrarError(500, "Error interno del servidor");
            }
        }
    }
    
    /**
     * Endpoint: POST /libros
     * Maneja la petición de creación de nuevo libro
     * Coordina Model y View sin contener lógica de negocio
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param anoPublicacion Año de publicación
     */
    public void agregarLibro(String titulo, String autor, Integer anoPublicacion) {
        try {
            // Coordina con el MODEL para crear el libro
            LibroDTO nuevoLibro = libroService.agregarLibro(titulo, autor, anoPublicacion);
            
            // Coordina con la VIEW para mostrar la respuesta exitosa (201 Created)
            view.mostrarLibroCreado(nuevoLibro);
            
        } catch (IllegalArgumentException e) {
            // Error 400 - Bad Request (datos inválidos)
            view.mostrarError(400, "Datos inválidos: " + e.getMessage());
            
        } catch (Exception e) {
            // Error 500 - Internal Server Error
            view.mostrarError(500, "Error interno del servidor: " + e.getMessage());
        }
    }
    
    /**
     * Simulación de endpoint REST - GET /libros/{id}
     * Retorna respuesta HTTP estructurada para APIs REST
     * @param id ID del libro
     * @return RestResponse con código HTTP y datos
     */
    public RestResponse<LibroDTO> getLibro(Long id) {
        try {
            LibroDTO libro = libroService.obtenerLibroPorId(id);
            return new RestResponse<>(200, "OK", libro);
        } catch (IllegalArgumentException e) {
            return new RestResponse<>(400, "Bad Request", null);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("404")) {
                return new RestResponse<>(404, "Not Found", null);
            }
            return new RestResponse<>(500, "Internal Server Error", null);
        }
    }
    
    /**
     * Simulación de endpoint REST - POST /libros
     * Retorna respuesta HTTP estructurada para APIs REST
     * @param request Datos del libro a crear
     * @return RestResponse con código HTTP y datos
     */
    public RestResponse<LibroDTO> postLibro(LibroRequest request) {
        try {
            LibroDTO nuevoLibro = libroService.agregarLibro(
                request.getTitulo(),
                request.getAutor(),
                request.getAnoPublicacion()
            );
            return new RestResponse<>(201, "Created", nuevoLibro);
        } catch (IllegalArgumentException e) {
            return new RestResponse<>(400, "Bad Request", null);
        } catch (Exception e) {
            return new RestResponse<>(500, "Internal Server Error", null);
        }
    }
    
    /**
     * Manejo interactivo del menú principal
     * Coordina la interacción usuario-sistema
     */
    public void mostrarMenuPrincipal() {
        view.mostrarMenu();
    }
    
    /**
     * Mostrar estadísticas del sistema
     * Coordina con Model y View para mostrar información del sistema
     */
    public void mostrarEstadisticas() {
        try {
            int totalLibros = libroService.contarLibros();
            view.mostrarEstadisticas(totalLibros);
        } catch (Exception e) {
            view.mostrarError(500, "Error al obtener estadísticas: " + e.getMessage());
        }
    }
    
    // ========================================================================
    // CLASES AUXILIARES PARA REST API
    // ========================================================================
    
    /**
     * Clase para encapsular requests de creación de libros
     */
    public static class LibroRequest {
        private String titulo;
        private String autor;
        private Integer anoPublicacion;
        
        /**
         * Constructor vacío
         */
        public LibroRequest() {
        }
        
        /**
         * Constructor completo
         */
        public LibroRequest(String titulo, String autor, Integer anoPublicacion) {
            this.titulo = titulo;
            this.autor = autor;
            this.anoPublicacion = anoPublicacion;
        }
        
        // Getters y Setters
        public String getTitulo() {
            return titulo;
        }
        
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
        
        public String getAutor() {
            return autor;
        }
        
        public void setAutor(String autor) {
            this.autor = autor;
        }
        
        public Integer getAnoPublicacion() {
            return anoPublicacion;
        }
        
        public void setAnoPublicacion(Integer anoPublicacion) {
            this.anoPublicacion = anoPublicacion;
        }
        
        @Override
        public String toString() {
            return "LibroRequest{" +
                    "titulo='" + titulo + '\'' +
                    ", autor='" + autor + '\'' +
                    ", anoPublicacion=" + anoPublicacion +
                    '}';
        }
    }
    
    /**
     * Clase para encapsular respuestas REST con código HTTP
     */
    public static class RestResponse<T> {
        private int status;
        private String message;
        private T data;
        
        /**
         * Constructor completo
         */
        public RestResponse(int status, String message, T data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }
        
        // Getters
        public int getStatus() {
            return status;
        }
        
        public String getMessage() {
            return message;
        }
        
        public T getData() {
            return data;
        }
        
        @Override
        public String toString() {
            return "RestResponse{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
}
