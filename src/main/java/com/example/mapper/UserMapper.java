package com.example.mapper;

import com.example.dto.UserResponseDto;
import com.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "userId")
    UserResponseDto toDto(User user);
}
