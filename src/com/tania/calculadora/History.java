package com.tania.calculadora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * History (historial de operaciones):
 * - Guarda cadenas de texto describiendo cada operación realizada.
 * - Expone la lista como inmutable hacia fuera (encapsulación).
 * - Ofrece un método "bonito" para imprimir en consola.
 */
public class History {
    private final List<String> entries = new ArrayList<>();

    /** Añade una entrada no vacía al historial. */
    public void add(String entry) {
        if (entry != null && !entry.isBlank()) {
            entries.add(entry);
        }
    }

    /** Devuelve una vista inmutable del historial (nadie puede modificarlo desde fuera). */
    public List<String> getAll() {
        return Collections.unmodifiableList(entries);
    }

    /** Representación "bonita" para mostrar en consola. */
    public String toPrettyString() {
        if (entries.isEmpty()) return "(Sin operaciones registradas)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entries.size(); i++) {
            sb.append(String.format("%2d) %s%n", i + 1, entries.get(i)));
        }
        return sb.toString();
    }
}