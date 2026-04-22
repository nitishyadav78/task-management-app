package com.dogratechnologies.task_management_app.ExceptionPackage;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
