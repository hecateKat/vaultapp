package com.kat.vaultapp.service.implementation;

import com.kat.vaultapp.dto.user.UserRegistrationRequestDto;
import com.kat.vaultapp.dto.user.UserResponseDto;
import com.kat.vaultapp.entity.role.Role;
import com.kat.vaultapp.entity.role.RoleName;
import com.kat.vaultapp.entity.user.User;
import com.kat.vaultapp.exception.RegistrationException;
import com.kat.vaultapp.mapper.UserMapper;
import com.kat.vaultapp.repository.RoleRepository;
import com.kat.vaultapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void test_register_should_register_user_successfully() throws RegistrationException {
        // given
        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto("testUser", "password123", "password123");
        Role role = new Role();
        role.setName(RoleName.ROLE_USER);

        User user = new User();
        user.setLogin(requestDto.login());
        user.setPassword("encodedPassword");
        user.setRoles(Set.of(role));

        User savedUser = new User();
        savedUser.setId(UUID.randomUUID());
        savedUser.setLogin(requestDto.login());
        savedUser.setPassword("encodedPassword");
        savedUser.setRoles(Set.of(role));

        UserResponseDto responseDto = new UserResponseDto(savedUser.getId(), "testUser");

        Mockito.when(userRepository.existsByLogin(requestDto.login())).thenReturn(false);
        Mockito.when(userMapper.toEntity(requestDto)).thenReturn(user);
        Mockito.when(passwordEncoder.encode(requestDto.password())).thenReturn("encodedPassword");
        Mockito.when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(role));
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);
        Mockito.when(userMapper.toResponseDto(savedUser)).thenReturn(responseDto);

        // when
        UserResponseDto actualResponse = userService.register(requestDto);

        // then
        Assertions.assertEquals(responseDto, actualResponse);
    }

    @Test
    void test_register_should_throw_exception_when_user_already_exists() {
        // given
        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto("testUser", "password123", "password123");

        Mockito.when(userRepository.existsByLogin(requestDto.login())).thenReturn(true);

        // when & then
        Assertions.assertThrows(RegistrationException.class, () -> userService.register(requestDto));
    }
}
