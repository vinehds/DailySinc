package com.vinehds.dailysinc.model.enums;

public enum ResponsabilityType {

    ADMIN(1), TECHLEAD(2), MEMBER(3);

    private int code;

    ResponsabilityType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ResponsabilityType valueOf(int code) {
        for (ResponsabilityType responsabilityType : ResponsabilityType.values()) {
            if (responsabilityType.getCode() == code) {
                return responsabilityType;
            }
        }
        throw new IllegalArgumentException("Invalid Responsibility code: " + code);
    }
}
