/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemadegestiondelibrosbiblioteca;

/**
 *
 * @author gian_
 */
import com.mycompany.sistemadegestiondelibrosbibliioteca.controller.LibroController;
import com.mycompany.sistemadegestiondelibrosbibliioteca.view.BibliotecaView;

/**
 * BibliotecaApp - Aplicación principal
 *
 * ENDPOINTS REST:
 * GET  /libros/{id}  - Obtener DTO de libro por ID
 * POST /libros       - Crear nuevo libro
 */
public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaView view = new BibliotecaView();
        LibroController controller = new LibroController(view);

        view.ejecutarSistemaInteractivo(controller);
    }
}