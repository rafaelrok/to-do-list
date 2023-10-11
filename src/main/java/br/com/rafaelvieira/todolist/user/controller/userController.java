package br.com.rafaelvieira.todolist.user.controller;

import br.com.rafaelvieira.todolist.user.domain.user;
import br.com.rafaelvieira.todolist.user.dto.userDto;
import br.com.rafaelvieira.todolist.user.mapper.userMapper;
import br.com.rafaelvieira.todolist.user.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/user")
@RestController
@Slf4j
@Api("user")
public class userController {
    private final userService userService;

    public userController(userService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated userDto userDto) {
        userService.save(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<userDto> findById(@PathVariable("id") Long id) {
        userDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(userService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<userDto>> pageQuery(userDto userDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<userDto> userPage = userService.findByCondition(userDto, pageable);
        return ResponseEntity.ok(userPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated userDto userDto, @PathVariable("id") Long id) {
        userService.update(userDto, id);
        return ResponseEntity.ok().build();
    }
}
