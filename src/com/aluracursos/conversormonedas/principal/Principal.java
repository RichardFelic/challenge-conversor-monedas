package com.aluracursos.conversormonedas.principal;

import com.aluracursos.conversormonedas.modelos.ApiClient;
import com.aluracursos.conversormonedas.util.Validador;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Validador validador = new Validador();  // Crear instancia de la clase Validador

        System.out.println("Bienvenido al conversor de monedas.");

        // Bucle principal para mostrar el menú y manejar las opciones del usuario
        while (true) {
            mostrarMenu();
            String opcion = scanner.nextLine().toUpperCase();

            switch (opcion) {
                case "1":  // Opción para realizar la conversión de monedas
                    realizarConversion(validador, scanner);
                    break;
                case "2":  // Opción para salir del programa
                    System.out.println("Saliendo del programa...");
                    return;  // Salir del programa
                default:  // Manejar opción inválida
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        }
    }

    // Metodo para mostrar el menú de opciones
    public static void mostrarMenu() {
        String menu = """
                ------------------------------------
                Por favor seleccione una opción:
                1. Convertir Monedas
                2. Salir
                ------------------------------------
                """;
        System.out.println(menu);
    }

    // Metodo para realizar la conversión de monedas
    public static void realizarConversion(Validador validador, Scanner scanner) {
        // Solicitar y validar moneda base
        String base = validador.solicitarMoneda(scanner, "Ingrese la moneda base (ISO 4217, ej. USD): ");

        // Solicitar y validar moneda objetivo
        String objetivo = validador.solicitarMoneda(scanner, "Ingrese la moneda objetivo (ISO 4217, ej. EUR): ");

        // Solicitar y validar cantidad a convertir
        double cantidad = validador.solicitarCantidad(scanner);

        try {
            // Obtener la tasa de conversión y calcular el resultado
            double tasaConversion = ApiClient.obtenerTasaConversion(base, objetivo);
            if (tasaConversion == 0.0) {
                System.out.println("No se pudo obtener la tasa de conversión entre " + base + " y " + objetivo + ".");
            } else {
                double resultado = cantidad * tasaConversion;
                System.out.println("Tasa de conversión " + base + " a " + objetivo + ": " + tasaConversion);
                System.out.println("Resultado: " + cantidad + " " + base + " = " + resultado + " " + objetivo);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la tasa de conversión: " + e.getMessage());
        }
    }
}
