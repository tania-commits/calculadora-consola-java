package com.tania.calculadora;

/**
 * InputValidator (utilidad de validación):
 * - Centraliza reglas simples (rango, no-negativo) para poder reutilizarlas
 *   desde ConsoleUI u otras capas.
 * - Lanza IllegalArgumentException con mensajes claros si no se cumple.
 */
public final class InputValidator {

    private InputValidator() { }

    /** Valida que value esté entre min y max (ambos inclusive). */
    public static void requireInRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " debe estar entre " + min + " y " + max + ".");
        }
    }

    /** Valida que n sea >= 0. Útil para factorial o índices. */
    public static void requireNonNegative(int n, String fieldName) {
        if (n < 0) {
            throw new IllegalArgumentException(fieldName + " debe ser >= 0.");
        }
    }
}