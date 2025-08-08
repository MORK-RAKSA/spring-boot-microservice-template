package com.raksa.app.services.Iservices;

import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.PaginationResponse;
import com.raksa.app.dtos.responses.UserResponseDto;

import java.util.List;


public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);

    List<UserResponseDto> getAllUser();

    UserResponseDto getUserById(String id);

    PaginationResponse<UserResponseDto> getUserByPaginated(int page, int size);

    void deleteAllUsers();

    void deleteAll();
}
