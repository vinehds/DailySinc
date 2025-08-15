package com.vinehds.dailysinc.model.enums;

public enum Responsability {

    ADMIN(1), TECHLEAD(2), MEMBER(3);

    private int code;

    Responsability(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Responsability valueOf(int code) {
        for (Responsability responsabilities : Responsability.values()) {
            if (responsabilities.getCode() == code) {
                return responsabilities;
            }
        }
        throw new IllegalArgumentException("Invalid Responsibility code: " + code);
    }
}
