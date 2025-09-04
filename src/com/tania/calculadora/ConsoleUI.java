package com.tania.calculadora;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * ConsoleUI (capa de presentación por consola):
 * - Se encarga de leer textos y números de forma robusta (con bucles y mensajes útiles).
 * - Separa totalmente la IO de la lógica de cálculo (buena práctica).
 * - Usa InputValidator para validar reglas concretas (rango, no-negativo).
 */
@SuppressWarnings("ClassCanBeRecord")
public class ConsoleUI {
    private final Scanner scanner;   // Lee del teclado (System.in)
    private final PrintStream out;   // Escribe en consola (System.out)

    public ConsoleUI(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    /** Muestra el menú principal. Solo imprime, no lee nada. */
    public void mostrarMenu() {
        println("");
        println("1) Sumar");
        println("2) Restar");
        println("3) Multiplicar");
        println("4) Dividir");
        println("5) Potencia");
        println("6) Factorial");
        println("7) Ver historial");
        println("8) Salir");
    }

    /** Lee un entero (repite hasta que el usuario escriba un número válido). */
    public int leerEntero(String prompt) {
        while (true) {
            print(prompt);
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException ex) {
                println("⚠️  Debes introducir un número entero válido.");
            }
        }
    }

    /**
     * Lee un entero y valida que esté dentro de [min, max].
     * Si no lo está, vuelve a pedirlo (usando InputValidator para centralizar la regla).
     */
    public int leerEnteroEnRango(String prompt, int min, int max) {
        while (true) {
            int n = leerEntero(prompt);
            try {
                InputValidator.requireInRange(n, min, max, "La opción");
                return n;
            } catch (IllegalArgumentException ex) {
                println("⚠️  " + ex.getMessage());
            }
        }
    }

    /**
     * Lee un entero y valida que sea >= 0.
     * Pensado para factorial u operaciones que no aceptan negativos.
     */
    public int leerEnteroNoNegativo(String prompt) {
        while (true) {
            int n = leerEntero(prompt);
            try {
                InputValidator.requireNonNegative(n, "El número");
                return n;
            } catch (IllegalArgumentException ex) {
                println("⚠️  " + ex.getMessage());
            }
        }
    }

    /**
     * Lee un double (acepta punto o coma como separador decimal).
     * Si hay error al parsear, lo indica y vuelve a pedir.
     */
    public double leerDouble(String prompt) {
        while (true) {
            print(prompt);
            String linea = scanner.nextLine().trim();
            try {
                // Permitimos usar coma o punto para decimales
                return Double.parseDouble(linea.replace(',', '.'));
            } catch (NumberFormatException ex) {
                println("⚠️  Debes introducir un número (usa punto o coma decimal).");
            }
        }
    }

    /** Pausa simple hasta pulsar ENTER (útil para que el usuario lea resultados). */
    public void pausa(String msg) {
        println(msg);
        scanner.nextLine();
    }

    // Pequeñas utilidades para imprimir
    public void println(String msg) { out.println(msg); }
    public void print(String msg)   { out.print(msg); }
}