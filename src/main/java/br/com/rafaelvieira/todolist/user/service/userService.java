package br.com.rafaelvieira.todolist.user.service;

import br.com.rafaelvieira.todolist.user.domain.user;
import br.com.rafaelvieira.todolist.user.dto.userDto;
import br.com.rafaelvieira.todolist.user.mapper.userMapper;
import br.com.rafaelvieira.todolist.user.repository.userRepository;
import br.com.rafaelvieira.todolist.user.service.handlers.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class userService {
    private final userRepository repository;
    private final userMapper userMapper;

    public userService(userRepository repository, userMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public userDto save(userDto userDto) {
        user entity = userMapper.toEntity(userDto);
        return userMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public userDto findById(Long id) {
        return userMapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)));
    }

    public Page<userDto> findByCondition(userDto userDto, Pageable pageable) {
        Page<user> entityPage = repository.findAll(pageable);
        List<user> entities = entityPage.getContent();
        return new PageImpl<>(userMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public userDto update(userDto userDto, Long id) {
        userDto data = findById(id);
        user entity = userMapper.toEntity(userDto);
        entity.setId(data.getId());
//        BeanUtil.copyProperties(data, entity);
        return save(userMapper.toDto(entity));
    }
}
