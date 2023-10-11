package br.com.rafaelvieira.todolist.user.repository;

import br.com.rafaelvieira.todolist.user.domain.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user, Long>, JpaSpecificationExecutor<user> {
}