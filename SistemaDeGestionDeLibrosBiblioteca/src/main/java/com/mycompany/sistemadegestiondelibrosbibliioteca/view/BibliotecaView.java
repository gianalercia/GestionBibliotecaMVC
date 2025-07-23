/*
 * Autor: Mauro Gomez
 */
package com.mycompany.sistemadegestiondelibrosbibliioteca.view;

import com.mycompany.sistemadegestiondelibrosbibliioteca.controller.LibroController;
import com.mycompany.sistemadegestiondelibrosbibliioteca.model.dto.LibroDTO;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase BibliotecaView - Implementa la interfaz de usuario del sistema
 * Sigue el patrón MVC (Modelo-Vista-Controlador)
 */
public class BibliotecaView {
    // Scanner para leer entrada del usuario
    private Scanner scanner;

    /**
     * Constructor - Inicializa el scanner para entrada de datos
     */
    public BibliotecaView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra los detalles de un libro encontrado
     * @param libro Objeto LibroDTO con la información a mostrar
     */
    public void mostrarLibroEncontrado(LibroDTO libro) {
        System.out.println("HTTP 200 OK");
        System.out.println("Libro encontrado:");
        System.out.println("ID: " + libro.getId());
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Año: " + libro.getAnoPublicacion());
        System.out.println(); // Salto de línea para mejor formato
    }

    /**
     * Muestra los detalles de un libro recién creado
     * @param libro Objeto LibroDTO con la información del nuevo libro
     */
    public void mostrarLibroCreado(LibroDTO libro) {
        System.out.println("HTTP 201 CREATED");
        System.out.println("Libro creado exitosamente:");
        System.out.println("ID: " + libro.getId());
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Año: " + libro.getAnoPublicacion());
        System.out.println();
    }

    /**
     * Muestra la lista completa de libros
     * @param libros Lista de objetos LibroDTO a mostrar
     */
    public void mostrarListaLibros(List<LibroDTO> libros) {
        System.out.println("HTTP 200 OK");
        System.out.println("Lista de todos los libros:");
        System.out.println("Total de libros: " + libros.size());
        System.out.println();

        if (libros.isEmpty()) {
            System.out.println("No hay libros en el sistema.");
        } else {
            // Iteramos sobre cada libro y mostramos sus datos
            for (LibroDTO libro : libros) {
                System.out.println("ID: " + libro.getId() +
                        " | Título: " + libro.getTitulo() +
                        " | Autor: " + libro.getAutor() +
                        " | Año: " + libro.getAnoPublicacion());
            }
        }
        System.out.println();
    }

    /**
     * Muestra mensajes de error con formato HTTP
     * @param codigoHttp Código de estado HTTP
     * @param mensaje Mensaje de error descriptivo
     */
    public void mostrarError(int codigoHttp, String mensaje) {
        String nombreEstado = obtenerNombreEstado(codigoHttp);
        System.out.println("HTTP " + codigoHttp + " " + nombreEstado);
        System.out.println("Mensaje: " + mensaje);
        System.out.println();
    }

    /**
     * Método principal que ejecuta el sistema interactivo
     * @param controller Controlador para manejar la lógica de negocio
     */
    public void ejecutarSistemaInteractivo(LibroController controller) {
        boolean continuar = true;

        // Bucle principal del sistema
        while (continuar) {
            try {
                mostrarMenu();
                int opcion = leerOpcion();

                // Switch para manejar las opciones del menú
                switch (opcion) {
                    case 1:
                        ejecutarBusquedaLibro(controller);
                        break;
                    case 2:
                        ejecutarAgregarLibro(controller);
                        break;
                    case 3:
                        ejecutarListarLibros(controller);
                        break;
                    case 4:
                        continuar = false;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida. Seleccione 1-4");
                        break;
                }

                // Pausa antes de continuar (excepto para salir)
                if (continuar && opcion >= 1 && opcion <= 3) {
                    System.out.println("Presione Enter para continuar...");
                    scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer
            }
        }
        scanner.close(); // Cerrar scanner al finalizar
    }

    /**
     * Maneja la búsqueda de un libro por ID
     * @param controller Controlador para realizar la búsqueda
     */
    private void ejecutarBusquedaLibro(LibroController controller) {
        System.out.println("=== Buscar libro por ID ===");

        try {
            System.out.print("Ingrese el ID del libro: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Limpiar buffer

            // Delegamos la búsqueda al controlador
            controller.obtenerLibro(id);

        } catch (InputMismatchException e) {
            scanner.nextLine();
            mostrarError(400, "ID inválido. Debe ser un número entero.");
        }
    }

    /**
     * Maneja la adición de un nuevo libro al sistema
     * @param controller Controlador para agregar el libro
     */
    private void ejecutarAgregarLibro(LibroController controller) {
        System.out.println("=== Agregar nuevo libro ===");

        try {
            // Solicitar datos del libro
            System.out.print("Ingrese el título: ");
            String titulo = scanner.nextLine().trim();

            System.out.print("Ingrese el autor: ");
            String autor = scanner.nextLine().trim();

            System.out.print("Ingrese el año de publicación: ");
            String anoStr = scanner.nextLine().trim();

            // Mostrar resumen de datos
            System.out.println("Datos a guardar:");
            System.out.println("Título: " + titulo);
            System.out.println("Autor: " + autor);
            System.out.println("Año: " + anoStr);

            // Confirmación del usuario
            System.out.print("¿Confirma? (s/n): ");
            String confirmacion = scanner.nextLine().trim().toLowerCase();

            if (confirmacion.equals("s") || confirmacion.equals("si")) {
                // Delegamos la creación al controlador
                controller.agregarLibro(titulo, autor, anoStr);
            } else {
                System.out.println("Operación cancelada.");
            }

        } catch (Exception e) {
            mostrarError(500, "Error al procesar datos: " + e.getMessage());
        }
    }

    /**
     * Muestra el menú principal de opciones
     */
    private void mostrarMenu() {
        System.out.println("\n=== Sistema de Gestión de Libros ===");
        System.out.println("1. Buscar libro por ID");
        System.out.println("2. Agregar nuevo libro");
        System.out.println("3. Mostrar todos los libros");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Lee y valida la opción seleccionada por el usuario
     * @return Opción seleccionada o -1 si es inválida
     */
    private int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            return opcion;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar buffer
            return -1; // Retornar valor inválido
        }
    }

    /**
     * Convierte códigos HTTP a sus nombres descriptivos
     * @param codigo Código de estado HTTP
     * @return Nombre descriptivo del estado
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
     * Maneja la solicitud para listar todos los libros
     * @param controller Controlador para obtener la lista
     */
    private void ejecutarListarLibros(LibroController controller) {
        System.out.println("=== Listar todos los libros ===");
        // Delegamos la operación al controlador
        controller.listarTodosLosLibros();
    }
}