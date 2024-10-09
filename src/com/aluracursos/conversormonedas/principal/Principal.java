package com.aluracursos.conversormonedas.principal;

import com.aluracursos.conversormonedas.modelos.ApiClient;
import com.aluracursos.conversormonedas.util.Validador;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Validador validador = new Validador();  // Crear instancia de la clase Validador

        System.out.println("Bienvenido al conversor de monedas.");

        while (true) {
            mostrarMenu();  // Mostrar opciones al usuario
            String opcion = scanner.nextLine().toUpperCase();  // Leer opción del usuario

            switch (opcion) {
                case "1":  // Caso 1: Convertir monedas
                    realizarConversion(validador, scanner);
                    break;

                case "2":  // Caso 2: Salir del programa
                    System.out.println("Saliendo del programa...");
                    System.exit(0);  // Terminar programa

                default:  // Opción inválida
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        }
    }

    // Metodo para mostrar el menu
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
        try {
            // Solicitar y validar moneda base
            String base = validador.solicitarMoneda(scanner, "Ingrese la moneda base (ISO 4217, ej. USD): ");
            if (base.equalsIgnoreCase("SALIR")) return;

            // Solicitar y validar moneda objetivo
            String objetivo = validador.solicitarMoneda(scanner, "Ingrese la moneda objetivo (ISO 4217, ej. EUR): ");
            if (objetivo.equalsIgnoreCase("SALIR")) return;

            // Solicitar y validar cantidad
            double cantidad = validador.solicitarCantidad(scanner);
            if (cantidad == -1) return;

            // Obtener tasa de conversión y realizar la conversión
            try {
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
        } catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
    }
}
