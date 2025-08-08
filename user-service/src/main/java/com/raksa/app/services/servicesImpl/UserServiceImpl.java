package com.raksa.app.services.servicesImpl;

import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.PaginationResponse;
import com.raksa.app.dtos.responses.UserResponseDto;
import com.raksa.app.enumz.Role;
import com.raksa.app.exception.ResponseMessage;
import com.raksa.app.exception.exceptionHandle.DuplicateEntityException;
import com.raksa.app.exception.exceptionHandle.ResourceNotFoundException;
import com.raksa.app.mapper.UserMapper;
import com.raksa.app.model.UserEntity;
import com.raksa.app.repository.UserRepository;
import com.raksa.app.services.Iservices.UserService;
import com.raksa.app.utils.LoggerFormaterUtils;
import com.raksa.app.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final WebClient productTesting;

    public Mono<ResponseMessage<String>> getProductTesting() {
        return productTesting.get()
                .uri("/test")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<String>>() {});
    }

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        UserEntity entity = userMapper.toEntity(requestDto);

        LoggerFormaterUtils.convertDtoToJson("Request Dto Service", requestDto );

        if (userRepository.existsByUsername(requestDto.getUsername())){
            throw new DuplicateEntityException(
                    String.format("This username: %s is already used", requestDto.getUsername())
            );
        }

        entity.setPassword(new BCryptPasswordEncoder().encode(requestDto.getPassword()));
        entity.setCreatedBy("SYSTEM");

        UserEntity saved = userRepository.save(entity);
        log.info("\n\n\tSaved User: {}\n\n", saved);
        return userMapper.toResponseDto(saved);
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(String id) {
        UserEntity entity = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                String.format("No user found with ID: %s.", id)));
        return userMapper.toResponseDto(entity);
    }

    public UserResponseDto getUserByUsername(String username){

        UserEntity entity = userRepository.findByUsername(username);
        if (entity == null) {
            throw new ResourceNotFoundException(
                    String.format("No user found with username: %s.", username));
        }
        return userMapper.toResponseDto(entity);
    }

    @Override
    public PaginationResponse<UserResponseDto> getUserByPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> entityPage = userRepository.findAll(pageable);
        Page<UserResponseDto> responses = entityPage.map(userMapper::toResponseDto);
        return PaginationUtils.toPaginationResponse(responses);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAllExceptByRole(Role.SUPERADMIN.toString());
        log.info("All users have been deleted but excepted role SUPERADMIN successfully.");
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
        log.info("All users have been deleted successfully.");
    }
}
