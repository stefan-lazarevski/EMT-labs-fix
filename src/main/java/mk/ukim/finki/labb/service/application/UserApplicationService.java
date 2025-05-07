package mk.ukim.finki.labb.service.application;

import mk.ukim.finki.labb.dto.CreateUserDto;
import mk.ukim.finki.labb.dto.DisplayUserDto;
import mk.ukim.finki.labb.dto.LoginResponseDto;
import mk.ukim.finki.labb.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
