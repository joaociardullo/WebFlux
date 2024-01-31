package br.com.curso.joaociardullo.webfluxcourse.mapper;

import br.com.curso.joaociardullo.webfluxcourse.entity.User;
import br.com.curso.joaociardullo.webfluxcourse.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest request);

}
