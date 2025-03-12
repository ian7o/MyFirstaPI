package com.rentsclients.rentsandclients.Error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class Error {
    private String msg;

    private String path;

    private String method;

    private Date timestamp;

    public Error(String msg, String path, String method, Date timestamp) {
        this.msg = msg;
        this.path = path;
        this.method = method;
        this.timestamp = timestamp;
    }
}