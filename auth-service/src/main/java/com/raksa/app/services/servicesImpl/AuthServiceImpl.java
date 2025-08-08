package com.raksa.app.services.servicesImpl;

import com.raksa.app.dtos.requests.UserRequestDto;
import com.raksa.app.dtos.responses.JwtTokenResponseDto;
import com.raksa.app.dtos.responses.UserResponseDto;
import com.raksa.app.exception.ResponseMessage;
import com.raksa.app.exception.exceptionHandle.BadCredentialsException;
import com.raksa.app.utils.JwtUtil;
import com.raksa.app.utils.LoggerFormaterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final WebClient userServiceWebClient;

    public Mono<ResponseMessage<List<UserResponseDto>>> getAllUser(String authHeader) {

        return userServiceWebClient.get()
                .uri("/get-all-users")
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<List<UserResponseDto>>>() {});
    }

    public Mono<ResponseMessage<UserResponseDto>> getUserById(String id) {
        return userServiceWebClient.get()
                .uri("/get-user-by-id?id=",id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<UserResponseDto>>() {});
    }

    public Mono<UserResponseDto> fetchUser(String username) {
        return userServiceWebClient.get()
                .uri("/get-user-by-username?username={username}", username)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<UserResponseDto>>() {})
                .map(ResponseMessage::getData);
    }

    private boolean validatePassword(String rawPassword, String encodedPassword){
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

//    public Mono<ResponseMessage<JwtTokenResponseDto>> login(@RequestBody UserRequestDto userRequestDto) {
//        return userServiceWebClient.get()
//                .uri("/get-user-by-username?username={username}", userRequestDto.getUsername())
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<UserResponseDto>>() {})
//                .flatMap(response -> {
//                    UserResponseDto user = response.getData();
//                    if (user == null) {
//                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
//                    }
//                    // Compare passwords (use BCrypt or your hashing method)
//                    boolean matches = new BCryptPasswordEncoder().matches(userRequestDto.getPassword(), user.getPassword());
//                    if (matches) {
//                        String token = JwtUtil.generateToken(user);
//                        JwtTokenResponseDto jwtTokenResponseDto = new JwtTokenResponseDto();
//                        jwtTokenResponseDto.setToken(token);
//                        return Mono.just(ResponseMessage.success("Login successful", jwtTokenResponseDto));
//                    } else {
//                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
//                    }
//                });
//    }

    public Mono<ResponseMessage<JwtTokenResponseDto>> login(@RequestBody UserRequestDto userRequestDto) {
        return fetchUser(userRequestDto.getUsername())
                .flatMap(user -> {
                    LoggerFormaterUtils.convertDtoToJson("Response User", user);

                    if (ObjectUtils.isEmpty(user)){
                        return Mono.error(new BadCredentialsException("Invalid credential: Username is not correct"));
                    }

//                    if (!user.getPassword().equals(userRequestDto.getUsername())) {
//                        return Mono.error(new BadCredentialsException("Invalid credential: Username is not correct"));
//                    }

                    if (validatePassword(userRequestDto.getPassword(), user.getPassword())) {
                        String token = JwtUtil.generateToken(user);
                        JwtTokenResponseDto jwtTokenResponseDto = new JwtTokenResponseDto();
                        jwtTokenResponseDto.setToken(token);
                        log.info("\n\n\t----------------- Login successful for user: {}\n", user.getUsername());
                        return Mono.just(ResponseMessage.success("Login successful", jwtTokenResponseDto));
                    } else {
                        return Mono.error(new BadCredentialsException("Invalid credential: Password is not correct"));
                    }
                });
    }

}
