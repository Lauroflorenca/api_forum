package alura.forum.api.domain.topico;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoTopico {
    ABERTO("Aberto"),
    FECHADO("Fechado");

    private final String value;

    SituacaoTopico(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SituacaoTopico fromValue(String value) {
        for (SituacaoTopico situacao : SituacaoTopico.values()) {
            if (situacao.value.equalsIgnoreCase(value)) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Valor inválido para SituacaoTopico: " + value);
    }

    public static boolean isValid(String value) {
        for (SituacaoTopico situacao : values()) {
            if (situacao.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}