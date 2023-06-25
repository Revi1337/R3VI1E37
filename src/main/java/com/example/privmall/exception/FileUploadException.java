package com.example.privmall.exception;

public class FileUploadException extends CustomRootException{

    public FileUploadException(String message) {
        super(message);
    }

    @Override
    public String getStatusCode() {
        return "400";
    }
}
