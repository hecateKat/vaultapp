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
import com.kat.vaultapp.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.existsByLogin(requestDto.login())) {
            throw new RegistrationException("This user can't be registered");
        }
        User user = userMapper.toEntity(requestDto);
        user.setLogin(requestDto.login());
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.setRoles(generateDefaultSetRoles());
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    private Set<Role> generateDefaultSetRoles() throws RegistrationException {
        Role roleFromDB = roleRepository.findByName(RoleName.getByType(DEFAULT_ROLE))
                .orElseThrow(() -> new RegistrationException(
                        String.format("Can't find %s in table roles: ", DEFAULT_ROLE)));
        return Set.of(roleFromDB);
    }
}
