package br.com.rafaelvieira.todolist.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AbstractDto<E> {

    private LocalDateTime createAt;

    private LocalDateTime lastModifiedAt;

    private String createdBy;

    private String lastModifiedBy;

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
