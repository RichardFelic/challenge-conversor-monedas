package com.aluracursos.conversormonedas.util;

import java.util.Scanner;

public class Validador {
    // Método para validar el código de moneda
    public String solicitarMoneda(Scanner scanner, String mensaje) {
        String moneda;
        while (true) {
            System.out.print(mensaje);
            moneda = scanner.nextLine().toUpperCase();
            if (moneda.matches("^[A-Z]{3}$")) {
                break;
            } else {
                System.out.println("Error: El código de moneda debe tener 3 letras (ej. USD, EUR). Intente de nuevo.");
            }
        }
        return moneda;
    }

    // Método para validar la cantidad a convertir
    public double solicitarCantidad(Scanner scanner) {
        double cantidad = -1;
        while (true) {
            System.out.print("Ingrese la cantidad a convertir: ");
            if (scanner.hasNextDouble()) {
                cantidad = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer
                if (cantidad > 0) {
                    break;
                } else {
                    System.out.println("Error: La cantidad debe ser un número positivo.");
                }
            } else {
                System.out.println("Error: Ingrese un número válido.");
                scanner.next(); // Limpiar entrada no válida
            }
        }
        return cantidad;
    }

    // Método para validar si el usuario desea continuar
    public boolean deseaContinuar(Scanner scanner) {
        String respuesta;
        while (true) {
            System.out.print("¿Desea realizar otra conversión? (s/n): ");
            respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                return true;
            } else if (respuesta.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Error: Por favor, responda con 's' o 'n'.");
            }
        }
    }
}
