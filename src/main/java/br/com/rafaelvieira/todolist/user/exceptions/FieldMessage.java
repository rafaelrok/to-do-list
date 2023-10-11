package br.com.rafaelvieira.todolist.user.exceptions;


import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class FieldMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    public FieldMessage() {
    }

    public FieldMessage(String fieldName, String message) {
        super();
        this.fieldName = fieldName;
        this.message = message;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}