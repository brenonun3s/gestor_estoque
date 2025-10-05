package br.com.estoque.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estoque.config.security.JwtUtil;
import br.com.estoque.dto.mapper.UserMapper;
import br.com.estoque.dto.request.UserLoginDTO;
import br.com.estoque.dto.request.UserRegisterDTO;
import br.com.estoque.dto.response.UserResponseDTO;
import br.com.estoque.exceptions.custom.EmailAlreadyExistsException;
import br.com.estoque.model.entity.User;
import br.com.estoque.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder encoder;
  private final JwtUtil jwtUtil;

  public UserResponseDTO register(UserRegisterDTO dto) {
    if (userRepository.existsByEmail(dto.email())) {
      throw new EmailAlreadyExistsException("Email já está em uso!");
    }

    User user = userMapper.toEntity(dto);
    user.setPassword(encoder.encode(dto.password()));

    User saved = userRepository.save(user);
    return userMapper.toResponseDto(saved);
  }

  public String login(UserLoginDTO dto) {
    UserDetails userDetails = loadUserByUsername(dto.email());

    if (encoder.matches(dto.password(), userDetails.getPassword())) {
      return jwtUtil.generateToken(dto.email());

    }
    throw new RuntimeException("Credenciais Inválidas");
  }

  public String refreshToken(String tokenAntigo) {
    String email = jwtUtil.extractUsername(tokenAntigo);
    return jwtUtil.generateToken(email);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado/ localizado!"));

    return org.springframework.security.core.userdetails.User
        .withUsername(user.getEmail())
        .password(user.getPassword())
        .build();
  }
}