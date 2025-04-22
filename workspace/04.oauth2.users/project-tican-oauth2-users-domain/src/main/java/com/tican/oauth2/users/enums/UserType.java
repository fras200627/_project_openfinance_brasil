package com.tican.oauth2.users.enums;

public enum UserType {
    ADMIN("ADMIN"),
    USER("CLIENT");

    private String type;

    UserType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
