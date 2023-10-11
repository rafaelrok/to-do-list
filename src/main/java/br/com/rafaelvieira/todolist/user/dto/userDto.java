package br.com.rafaelvieira.todolist.user.dto;

import br.com.rafaelvieira.todolist.user.annotation.CheckEmail;
import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@ApiModel()
public class userDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    @NotBlank
    private String name;
    @CheckEmail
    @Size(max = 255)
    @NotBlank
    private String email;
    @Size(max = 255)
    @NotBlank
    private String password;
    @NotNull
    private Boolean active;
    @Size(max = 255)
    @NotBlank
    private String role;

    public userDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setRole(String role) {
        this.role = role;
    }

}