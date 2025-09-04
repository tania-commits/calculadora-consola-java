package com.tania.calculadora;

import java.util.Optional;

/**
 * Calculator (lógica de negocio):
 * - Contiene funciones puras de cálculo (no leen teclado, no escriben en consola).
 * - Así es fácil de probar con tests unitarios en el futuro (por eso no hay System.in/out aquí).
 */
public final class Calculator {

    // Clase de utilidad: constructor privado para evitar instanciación
    private Calculator() { }

    /** Suma simple: a + b */
    public static double suma(double a, double b) {
        return a + b;
    }

    /** Resta simple: a - b */
    public static double resta(double a, double b) {
        return a - b;
    }

    /** Multiplicación simple: a * b */
    public static double multiplicacion(double a, double b) {
        return a * b;
    }

    /**
     * División segura: devuelve Optional.empty() si el divisor es 0.
     * Ventaja: obligamos a quien llama a tratar el caso "no hay resultado".
     */
    public static Optional<Double> divisionSegura(double a, double b) {
        if (b == 0.0) return Optional.empty();
        return Optional.of(a / b);
    }

    /**
     * Potencia base^exponente (exponente entero, puede ser negativo).
     * Implementada con "exponentiation by squaring" (rápida y clásica).
     * Si exponente < 0, devolvemos 1/(base^|exp|).
     */
    public static double potencia(double base, int exponente) {
        if (exponente == 0) return 1.0;

        boolean negativo = exponente < 0;
        long e = Math.abs((long) exponente); // usamos long para evitar overflow al negar Integer.MIN_VALUE

        double resultado = 1.0;
        double b = base;

        while (e > 0) {
            if ((e & 1L) == 1L) {
                resultado *= b; // si el bit actual es 1, multiplicamos
            }
            b *= b;   // elevamos base al cuadrado
            e >>= 1;  // avanzamos al siguiente bit
        }

        return negativo ? 1.0 / resultado : resultado;
    }

    /**
     * Factorial iterativo para n >= 0.
     * - Lanza IllegalArgumentException si n < 0 (regla de dominio).
     * - Usa long: hasta 20! cabe sin overflow; más allá desborda (decisión consciente).
     */
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n debe ser >= 0");
        long res = 1L;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}