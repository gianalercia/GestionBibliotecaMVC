/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadegestiondelibrosbibliioteca.view;

/**
 *
 * @author gian_
 */
import com.mycompany.sistemadegestiondelibrosbibliioteca.model.dto.LibroDTO;
import java.util.Scanner;

/**
 * BibliotecaView - Capa de presentación MVC
 * Maneja toda la interfaz de usuario y presentación de datos
 * NO contiene lógica de negocio, solo presentación
 */
public class BibliotecaView {
    private Scanner scanner;
    
    /**
     * Constructor
     */
    public BibliotecaView() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Muestra un libro encontrado exitosamente (HTTP 200 OK)
     * @param libro LibroDTO a mostrar
     */
    public void mostrarLibroEncontrado(LibroDTO libro) {
        System.out.println("✅ HTTP 200 OK");
        System.out.println("📖 Libro encontrado:");
        System.out.println("   🆔 ID: " + libro.getId());
        System.out.println("   📚 Título: " + libro.getTitulo());
        System.out.println("   ✍️  Autor: " + libro.getAutor());
        System.out.println("   📅 Año: " + libro.getAnoPublicacion());
        System.out.println();
    }
    
    /**
     * Muestra un libro creado exitosamente (HTTP 201 Created)
     * @param libro LibroDTO del libro creado
     */
    public void mostrarLibroCreado(LibroDTO libro) {
        System.out.println("✅ HTTP 201 CREATED");
        System.out.println("📚 Nuevo libro agregado exitosamente:");
        System.out.println("   🆔 ID: " + libro.getId());
        System.out.println("   📚 Título: " + libro.getTitulo());
        System.out.println("   ✍️  Autor: " + libro.getAutor());
        System.out.println("   📅 Año: " + libro.getAnoPublicacion());
        System.out.println();
    }
    
    /**
     * Muestra errores HTTP con sus códigos correspondientes
     * @param codigoHttp Código de estado HTTP
     * @param mensaje Mensaje descriptivo del error
     */
    public void mostrarError(int codigoHttp, String mensaje) {
        String emoji = obtenerEmojiError(codigoHttp);
        String nombreEstado = obtenerNombreEstado(codigoHttp);
        
        System.out.println(emoji + " HTTP " + codigoHttp + " " + nombreEstado);
        System.out.println("   💬 Mensaje: " + mensaje);
        System.out.println();
    }
    
    /**
     * Muestra el menú principal del sistema
     */
    public void mostrarMenu() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║        SISTEMA DE GESTIÓN DE LIBROS       ║");
        System.out.println("║              (Arquitectura MVC)            ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║  1. 🔍 Buscar libro por ID                ║");
        System.out.println("║  2. ➕ Agregar nuevo libro                 ║");
        System.out.println("║  3. 📊 Ver estadísticas                   ║");
        System.out.println("║  4. 🚪 Salir del sistema                  ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.print("👉 Seleccione una opción (1-4): ");
    }
    
    /**
     * Muestra encabezado del sistema con información de arquitectura
     */
    public void mostrarEncabezado() {
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("    📚 SISTEMA DE GESTIÓN DE LIBROS - ARQUITECTURA MVC    ");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("🏗️  ARQUITECTURA:");
        System.out.println("   📋 MODEL: Entity + DTO + DAO + Service");
        System.out.println("   🎮 CONTROLLER: LibroController (Coordinación + REST)");
        System.out.println("   🖥️  VIEW: BibliotecaView (Presentación)");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("⚡ FUNCIONALIDADES:");
        System.out.println("   ✅ Buscar libro por ID");
        System.out.println("   ✅ Agregar nuevo libro");
        System.out.println("   ✅ Validaciones de datos");
        System.out.println("   ✅ Manejo de errores HTTP (400, 404, 500)");
        System.out.println("   ✅ Base de datos simulada en memoria");
        System.out.println("════════════════════════════════════════════════════════\n");
    }
    
    /**
     * Solicita ID de libro al usuario
     * @return ID ingresado por el usuario
     */
    public Long solicitarIdLibro() {
        System.out.print("🆔 Ingrese el ID del libro: ");
        try {
            return scanner.nextLong();
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer
            return null;
        }
    }
    
    /**
     * Solicita datos para crear un nuevo libro
     * @return Array con [titulo, autor, año] o null si hay error
     */
    public String[] solicitarDatosNuevoLibro() {
        try {
            scanner.nextLine(); // Limpiar buffer
            
            System.out.print("📚 Ingrese el título del libro: ");
            String titulo = scanner.nextLine();
            
            System.out.print("✍️  Ingrese el autor del libro: ");
            String autor = scanner.nextLine();
            
            System.out.print("📅 Ingrese el año de publicación: ");
            String anoStr = scanner.nextLine();
            
            return new String[]{titulo, autor, anoStr};
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Muestra estadísticas del sistema
     * @param totalLibros Número total de libros
     */
    public void mostrarEstadisticas(int totalLibros) {
        System.out.println("📊 ESTADÍSTICAS DEL SISTEMA");
        System.out.println("═══════════════════════════");
        System.out.println("📚 Total de libros: " + totalLibros);
        System.out.println("🏗️  Arquitectura: MVC (Model-View-Controller)");
        System.out.println("💾 Base de datos: HashMap en memoria");
        System.out.println("🌐 Endpoints REST: GET /libros/{id}, POST /libros");
        System.out.println();
    }
    
    /**
     * Muestra mensaje de despedida
     */
    public void mostrarDespedida() {
        System.out.println("👋 ¡Gracias por usar el Sistema de Gestión de Libros!");
        System.out.println("🎓 Trabajo Práctico - Arquitectura MVC en Java");
        System.out.println("═══════════════════════════════════════════════════");
    }
    
    /**
     * Muestra separador para casos de prueba
     * @param numeroCaso Número del caso de prueba
     * @param descripcion Descripción del caso
     */
    public void mostrarCasoPrueba(int numeroCaso, String descripcion) {
        System.out.println("🧪 CASO " + numeroCaso + ": " + descripcion);
        System.out.println("───────────────────────────────────────────");
    }
    
    /**
     * Muestra encabezado para demostración de endpoints REST
     */
    public void mostrarEncabezadoREST() {
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("🌟 DEMOSTRACIÓN DE ENDPOINTS REST:");
        System.out.println("════════════════════════════════════════════════════════");
    }
    
    /**
     * Muestra información de un endpoint REST
     * @param metodo Método HTTP (GET, POST, etc.)
     * @param endpoint URL del endpoint
     */
    public void mostrarEndpoint(String metodo, String endpoint) {
        System.out.println("📡 " + metodo + " " + endpoint);
    }
    
    /**
     * Espera que el usuario presione Enter para continuar
     */
    public void esperarEnter() {
        System.out.print("👉 Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    // ========================================================================
    // MÉTODOS AUXILIARES PRIVADOS
    // ========================================================================
    
    /**
     * Obtiene el emoji correspondiente según el código de error HTTP
     * @param codigoHttp Código HTTP
     * @return Emoji representativo
     */
    private String obtenerEmojiError(int codigoHttp) {
        switch (codigoHttp) {
            case 400: return "⚠️";  // Bad Request
            case 404: return "🔍"; // Not Found
            case 500: return "❌"; // Internal Server Error
            default: return "🚨";  // General Error
        }
    }
    
    /**
     * Obtiene el nombre del estado HTTP
     * @param codigo Código HTTP
     * @return Nombre del estado
     */
    private String obtenerNombreEstado(int codigo) {
        switch (codigo) {
            case 200: return "OK";
            case 201: return "CREATED";
            case 400: return "BAD REQUEST";
            case 404: return "NOT FOUND";
            case 500: return "INTERNAL SERVER ERROR";
            default: return "UNKNOWN";
        }
    }
    
    /**
     * Cierra el scanner (llamar al finalizar la aplicación)
     */
    public void cerrar() {
        if (scanner != null) {
            scanner.close();
        }
    }
}