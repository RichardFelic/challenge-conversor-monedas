package com.aluracursos.conversormonedas.util;

import java.util.Scanner;

public class Validador {
    // Metodo para solicitar y validar el código de moneda
    public String solicitarMoneda(Scanner scanner, String mensaje) {
        String moneda;
        while (true) {
            System.out.print(mensaje);
            moneda = scanner.nextLine().toUpperCase();
            // Verificar si el código de moneda tiene 3 letras
            if (moneda.matches("^[A-Z]{3}$")) {
                return moneda;  // Retornar moneda válida
            } else {
                System.out.println("Error: El código de moneda debe tener 3 letras (ej. USD, EUR). Intente de nuevo.");
            }
        }
    }

    // Metodo para solicitar y validar la cantidad a convertir
    public double solicitarCantidad(Scanner scanner) {
        double cantidad;
        while (true) {
            System.out.print("Ingrese la cantidad a convertir: ");
            // Verificar si la entrada es un número
            if (scanner.hasNextDouble()) {
                cantidad = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer
                if (cantidad > 0) {
                    return cantidad;  // Retornar cantidad válida
                } else {
                    System.out.println("Error: La cantidad debe ser un número positivo.");
                }
            } else {
                System.out.println("Error: Ingrese un número válido.");
                scanner.next(); // Limpiar entrada no válida
            }
        }
    }
}
