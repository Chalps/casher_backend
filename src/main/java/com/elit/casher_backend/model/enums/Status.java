package com.elit.casher_backend.model.enums;

public enum Status {
    BLOQUEADO(0, "Bloqueado"),
    DESBLOQUEADO(1, "Desbloqueado"),
    CURSANDO(2, "Cursando"),
    CONCLUIDO(3, "Concluido");

    private int cod;
    private String description;

    private Status(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static UserType toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (UserType x : UserType.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
