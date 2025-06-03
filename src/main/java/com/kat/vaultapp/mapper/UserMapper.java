package com.kat.vaultapp.mapper;

import com.kat.vaultapp.config.MapperConfig;
import com.kat.vaultapp.dto.user.UserRegistrationRequestDto;
import com.kat.vaultapp.dto.user.UserResponseDto;
import com.kat.vaultapp.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    User toEntity(UserRegistrationRequestDto requestDto);

    UserResponseDto toResponseDto(User user);
}
