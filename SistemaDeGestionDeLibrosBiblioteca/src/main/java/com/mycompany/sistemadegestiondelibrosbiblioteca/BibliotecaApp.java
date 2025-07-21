/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemadegestiondelibrosbiblioteca;

/**
 *
 * @author gian_
 */
import com.mycompany.sistemadegestiondelibrosbibliioteca.controller.LibroController;
import com.mycompany.sistemadegestiondelibrosbibliioteca.controller.LibroController.LibroRequest;
import com.mycompany.sistemadegestiondelibrosbibliioteca.controller.LibroController.RestResponse;
import com.mycompany.sistemadegestiondelibrosbibliioteca.model.dto.LibroDTO;
import com.mycompany.sistemadegestiondelibrosbibliioteca.view.BibliotecaView;

/**
 * BibliotecaApp - Aplicación principal
 * Demuestra el funcionamiento completo del sistema MVC
 * Incluye casos de prueba y ejemplos de uso
 */
public class BibliotecaApp {
    
    /**
     * Método principal - Punto de entrada de la aplicación
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Inicializar componentes MVC
        BibliotecaView view = new BibliotecaView();
        LibroController controller = new LibroController();
        
        // Mostrar encabezado del sistema
        view.mostrarEncabezado();
        
        // === DEMOSTRACIÓN AUTOMÁTICA DEL SISTEMA MVC ===
        ejecutarDemostracionAutomatica(view, controller);
        
        // === DEMOSTRACIÓN DE ENDPOINTS REST ===
        ejecutarDemostracionREST(view, controller);
        
        // === MENÚ INTERACTIVO (OPCIONAL) ===
        // ejecutarMenuInteractivo(view, controller);
        
        System.out.println("🎯 DEMOSTRACIÓN COMPLETADA - ARQUITECTURA MVC EXITOSA");
        view.mostrarDespedida();
    }
    
    /**
     * Ejecuta demostración automática de todos los casos de uso
     * @param view Vista para mostrar resultados
     * @param controller Controlador para coordinar operaciones
     */
    private static void ejecutarDemostracionAutomatica(BibliotecaView view, LibroController controller) {
        System.out.println("🚀 INICIANDO DEMOSTRACIÓN AUTOMÁTICA DEL SISTEMA MVC\n");
        
        // Caso 1: Búsqueda exitosa de libro existente
        view.mostrarCasoPrueba(1, "Búsqueda exitosa de libro (ID: 1)");
        controller.obtenerLibro(1L);
        
        // Caso 2: Libro no encontrado - Error 404
        view.mostrarCasoPrueba(2, "Libro no encontrado - Error 404 (ID: 999)");
        controller.obtenerLibro(999L);
        
        // Caso 3: ID inválido - Error 400
        view.mostrarCasoPrueba(3, "ID inválido - Error 400 (ID: null)");
        controller.obtenerLibro(null);
        
        // Caso 4: ID negativo - Error 400
        view.mostrarCasoPrueba(4, "ID negativo - Error 400 (ID: -5)");
        controller.obtenerLibro(-5L);
        
        // Caso 5: Agregar libro exitosamente
        view.mostrarCasoPrueba(5, "Agregar nuevo libro exitosamente");
        controller.agregarLibro("Clean Code", "Robert C. Martin", 2008);
        
        // Caso 6: Título vacío - Error 400
        view.mostrarCasoPrueba(6, "Título vacío - Error 400");
        controller.agregarLibro("", "Autor Test", 2023);
        
        // Caso 7: Autor nulo - Error 400
        view.mostrarCasoPrueba(7, "Autor nulo - Error 400");
        controller.agregarLibro("Libro Test", null, 2023);
        
        // Caso 8: Año futuro - Error 400
        view.mostrarCasoPrueba(8, "Año futuro - Error 400");
        controller.agregarLibro("Libro Futuro", "Autor Futuro", 2050);
        
        // Caso 9: Verificar que el libro nuevo se guardó correctamente
        view.mostrarCasoPrueba(9, "Verificar libro guardado (ID: 6)");
        controller.obtenerLibro(6L);
        
        // Caso 10: Agregar otro libro para demostrar incremento de ID
        view.mostrarCasoPrueba(10, "Agregar segundo libro nuevo");
        controller.agregarLibro("Design Patterns", "Gang of Four", 1994);
        
        // Caso 11: Mostrar estadísticas del sistema
        view.mostrarCasoPrueba(11, "Estadísticas del sistema");
        controller.mostrarEstadisticas();
    }
    
    /**
     * Ejecuta demostración de endpoints REST
     * @param view Vista para mostrar resultados
     * @param controller Controlador para manejar endpoints
     */
    private static void ejecutarDemostracionREST(BibliotecaView view, LibroController controller) {
        view.mostrarEncabezadoREST();
        
        // REST Endpoint 1: GET /libros/1 (Exitoso)
        view.mostrarEndpoint("GET", "/libros/1");
        RestResponse<LibroDTO> response1 = controller.getLibro(1L);
        System.out.println("   📋 Respuesta: " + response1);
        System.out.println();
        
        // REST Endpoint 2: GET /libros/404 (Not Found)
        view.mostrarEndpoint("GET", "/libros/404");
        RestResponse<LibroDTO> response2 = controller.getLibro(404L);
        System.out.println("   📋 Respuesta: " + response2);
        System.out.println();
        
        // REST Endpoint 3: GET /libros/null (Bad Request)
        view.mostrarEndpoint("GET", "/libros/null");
        RestResponse<LibroDTO> response3 = controller.getLibro(null);
        System.out.println("   📋 Respuesta: " + response3);
        System.out.println();
        
        // REST Endpoint 4: POST /libros (Exitoso)
        view.mostrarEndpoint("POST", "/libros");
        LibroRequest request1 = new LibroRequest("The Pragmatic Programmer", "David Thomas", 1999);
        RestResponse<LibroDTO> response4 = controller.postLibro(request1);
        System.out.println("   📋 Request: " + request1);
        System.out.println("   📋 Respuesta: " + response4);
        System.out.println();
        
        // REST Endpoint 5: POST /libros (Bad Request - título vacío)
        view.mostrarEndpoint("POST", "/libros (Bad Request)");
        LibroRequest request2 = new LibroRequest("", "Autor Inválido", 2023);
        RestResponse<LibroDTO> response5 = controller.postLibro(request2);
        System.out.println("   📋 Request: " + request2);
        System.out.println("   📋 Respuesta: " + response5);
        System.out.println();
        
        System.out.println("════════════════════════════════════════════════════════\n");
    }
    
    /**
     * Ejecuta menú interactivo para uso manual del sistema
     * @param view Vista para mostrar menú
     * @param controller Controlador para manejar operaciones
     */
    private static void ejecutarMenuInteractivo(BibliotecaView view, LibroController controller) {
        System.out.println("🎮 MODO INTERACTIVO ACTIVADO");
        System.out.println("════════════════════════════\n");
        
        boolean continuar = true;
        
        while (continuar) {
            try {
                controller.mostrarMenuPrincipal();
                
                // Simular entrada del usuario (en aplicación real usaría Scanner)
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                int opcion = scanner.nextInt();
                
                switch (opcion) {
                    case 1:
                        // Buscar libro por ID
                        Long id = view.solicitarIdLibro();
                        if (id != null) {
                            controller.obtenerLibro(id);
                        } else {
                            view.mostrarError(400, "ID inválido ingresado");
                        }
                        break;
                        
                    case 2:
                        // Agregar nuevo libro
                        String[] datos = view.solicitarDatosNuevoLibro();
                        if (datos != null && datos.length == 3) {
                            try {
                                Integer ano = Integer.parseInt(datos[2]);
                                controller.agregarLibro(datos[0], datos[1], ano);
                            } catch (NumberFormatException e) {
                                view.mostrarError(400, "Año de publicación inválido");
                            }
                        } else {
                            view.mostrarError(400, "Datos incompletos o inválidos");
                        }
                        break;
                        
                    case 3:
                        // Ver estadísticas
                        controller.mostrarEstadisticas();
                        break;
                        
                    case 4:
                        // Salir
                        continuar = false;
                        break;
                        
                    default:
                        view.mostrarError(400, "Opción no válida. Seleccione 1-4");
                        break;
                }
                
                if (continuar && opcion >= 1 && opcion <= 3) {
                    view.esperarEnter();
                }
                
            } catch (Exception e) {
                view.mostrarError(500, "Error en la entrada de datos: " + e.getMessage());
                continuar = false;
            }
        }
        
        view.cerrar(); // Cerrar scanner
    }
    
    /**
     * Demuestra el patrón MVC paso a paso
     */
    private static void demostrarPatronMVC() {
        System.out.println("🎓 DEMOSTRACIÓN DEL PATRÓN MVC:");
        System.out.println("════════════════════════════════");
        System.out.println("1️⃣  CONTROLLER recibe petición HTTP");
        System.out.println("2️⃣  CONTROLLER llama al MODEL (Service)");
        System.out.println("3️⃣  MODEL usa DAO para datos");
        System.out.println("4️⃣  MODEL aplica validaciones");
        System.out.println("5️⃣  MODEL retorna resultado al CONTROLLER");
        System.out.println("6️⃣  CONTROLLER llama a VIEW");
        System.out.println("7️⃣  VIEW presenta información al usuario");
        System.out.println("════════════════════════════════\n");
    }
}