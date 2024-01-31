package br.com.curso.joaociardullo.webfluxcourse.service;

import br.com.curso.joaociardullo.webfluxcourse.entity.User;
import br.com.curso.joaociardullo.webfluxcourse.mapper.UserMapper;
import br.com.curso.joaociardullo.webfluxcourse.model.request.UserRequest;
import br.com.curso.joaociardullo.webfluxcourse.repositorie.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Mono<User> save(final UserRequest request) {
        return repository.save(mapper.toEntity(request));
    }
}
