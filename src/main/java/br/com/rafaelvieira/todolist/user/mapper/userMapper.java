package br.com.rafaelvieira.todolist.user.mapper;

import br.com.rafaelvieira.todolist.user.domain.user;
import br.com.rafaelvieira.todolist.user.dto.userDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface userMapper extends EntityMapper<userDto, user> {
}