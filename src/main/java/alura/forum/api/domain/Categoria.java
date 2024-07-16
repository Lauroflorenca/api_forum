package alura.forum.api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Categoria {
    BACK("Back"),
    FRONT("Front"),
    BANCO("Banco"),
    UX("Ux");

    private final String value;

    Categoria(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Categoria fromValue(String value) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.value.equalsIgnoreCase(value)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Categoria: " + value);
    }
}


