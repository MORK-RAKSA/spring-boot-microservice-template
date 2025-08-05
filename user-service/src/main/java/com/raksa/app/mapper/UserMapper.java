package com.raksa.app.mapper;

import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.UserResponseDto;
import com.raksa.app.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserEntity toEntity(UserRequestDto dto);

    UserResponseDto toResponseDto(UserEntity entity);
}
