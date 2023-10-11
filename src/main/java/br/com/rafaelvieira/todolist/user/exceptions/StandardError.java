package br.com.rafaelvieira.todolist.user.exceptions;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
public class StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError() {
        // TODO document why this constructor is empty
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

