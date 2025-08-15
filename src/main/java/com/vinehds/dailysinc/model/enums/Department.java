package com.vinehds.dailysinc.model.enums;

public enum Department {

    DEV_WEB(1), DEV_DESK(2), OTHER(3);

    private int code;

    Department(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Department valueOf(int code) {
        for (Department departments : Department.values()) {
            if (departments.getCode() == code) {
                return departments;
            }
        }
        throw new IllegalArgumentException("Invalid department code: " + code);
    }

}
