package com.tican.oauth2.users.enums;

public enum UserStatus {
    ENABLED("ENABLED"),
    EXPIRED("EXPIRED"),
    LOCKED("LOCKED");

    private String status;

    UserStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
