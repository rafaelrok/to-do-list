package br.com.rafaelvieira.todolist.user.controller;

import br.com.rafaelvieira.todolist.user.dto.userDto;

import java.util.Collections;
import java.util.List;

public class userBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static userDto getDto() {
        userDto dto = new userDto();
        dto.setId("1");
        return dto;
    }
}
