package com.elit.casher_backend.model.enums;

public enum UserType {
    ADMIN(0, "Administrador"),
    COMUM(1, "Comum"),
    RESPONSAVEL(2, "Responsável");

    private int cod;
    private String description;

    private UserType(int cod, String description) {
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
