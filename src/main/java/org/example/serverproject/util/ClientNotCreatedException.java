package org.example.serverproject.util;

public class ClientNotCreatedException extends RuntimeException{
    public ClientNotCreatedException(String msg){
        super(msg);
    }
}
