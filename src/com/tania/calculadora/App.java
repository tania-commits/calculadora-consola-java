package com.tania.calculadora;

import java.util.Optional;

/**
 * APP (capa de orquestación):
 * - Muestra el menú, pide datos a la UI y llama a la lógica de negocio (Calculator).
 * - Registra cada operación en History.
 * - NO hace cálculos por sí misma y NO interactúa con System.in/out directamente:
 *   delega eso en ConsoleUI (buena separación de responsabilidades).
 */
public class App {

    public static void main(String[] args) {
        // Creamos la UI de consola y el historial
        ConsoleUI ui = new ConsoleUI(System.in, System.out);
        History history = new History();

        ui.println("======================================");
        ui.println("   CALCULADORA DE CONSOLA - JAVA");
        ui.println("======================================");

        boolean salir = false;
        while (!salir) {
            // 1) Mostramos el menú principal
            ui.mostrarMenu();

            // 2) Leemos opción válida (1..8). ConsoleUI gestiona la validación.
            int opcion = ui.leerEnteroEnRango("Elige una opción (1-8): ", 1, 8);

            // 3) Ejecutamos en función de la opción elegida
            switch (opcion) {
                case 1 -> { // Suma
                    double a = ui.leerDouble("Introduce el primer número: ");
                    double b = ui.leerDouble("Introduce el segundo número: ");
                    double res = Calculator.suma(a, b);
                    ui.println("Resultado: " + res);
                    history.add("Suma: " + a + " + " + b + " = " + res);
                }
                case 2 -> { // Resta
                    double a = ui.leerDouble("Introduce el minuendo: ");
                    double b = ui.leerDouble("Introduce el sustraendo: ");
                    double res = Calculator.resta(a, b);
                    ui.println("Resultado: " + res);
                    history.add("Resta: " + a + " - " + b + " = " + res);
                }
                case 3 -> { // Multiplicación
                    double a = ui.leerDouble("Introduce el primer factor: ");
                    double b = ui.leerDouble("Introduce el segundo factor: ");
                    double res = Calculator.multiplicacion(a, b);
                    ui.println("Resultado: " + res);
                    history.add("Multiplicación: " + a + " * " + b + " = " + res);
                }
                case 4 -> { // División segura (si b == 0, devolvemos vacío)
                    double a = ui.leerDouble("Introduce el dividendo: ");
                    double b = ui.leerDouble("Introduce el divisor: ");
                    Optional<Double> res = Calculator.divisionSegura(a, b);
                    if (res.isPresent()) {
                        ui.println("Resultado: " + res.get());
                        history.add("División: " + a + " / " + b + " = " + res.get());
                    } else {
                        ui.println("⚠️  Error: no se puede dividir entre cero.");
                        history.add("División fallida: " + a + " / " + b + " (divisor = 0)");
                    }
                }
                case 5 -> { // Potencia base^exponente (exponente entero)
                    double base = ui.leerDouble("Introduce la base: ");
                    int exp = ui.leerEntero("Introduce el exponente (entero, puede ser negativo): ");
                    double res = Calculator.potencia(base, exp);
                    ui.println("Resultado: " + res);
                    history.add("Potencia: " + base + " ^ " + exp + " = " + res);
                }
                case 6 -> { // Factorial (solo n >= 0)
                    int n = ui.leerEnteroNoNegativo("Introduce un número entero >= 0: ");
                    long res = Calculator.factorial(n);
                    ui.println("Resultado: " + res);
                    history.add("Factorial: " + n + "! = " + res);
                }
                case 7 -> { // Mostrar historial
                    ui.println("--- HISTORIAL ---");
                    ui.println(history.toPrettyString());
                }
                case 8 -> { // Salir
                    salir = true;
                    ui.println("¡Gracias por usar la calculadora! 👋");
                }
                default -> ui.println("Opción no válida."); // No debería pasar gracias a la validación
            }

            // Pequeña pausa antes de volver al menú (si no salimos)
            if (!salir) {
                ui.println("");
                ui.pausa("Pulsa ENTER para continuar...");
            }
        }
    }
}