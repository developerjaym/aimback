package com.jaymansmann.chat.service.auth;

/**
 *
 */
import javax.validation.Valid;

import com.jaymansmann.chat.db.entity.auth.User;
import com.jaymansmann.chat.db.repository.auth.UserRepository;
import com.jaymansmann.chat.dto.auth.SignUpRequest;
import com.jaymansmann.chat.dto.auth.UserDTO;
import com.jaymansmann.chat.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/**
 * @author Jay
 *
 */
@Service
@Slf4j
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    public User getUser(UserPrincipal user) {
        return userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

    public UserDTO signUp(SignUpRequest signUpRequest) {

        if(existsByUsername(signUpRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        // Creating user's account
        User user = this.mapper.map(signUpRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void deleteById(Long id, UserPrincipal principal) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(!principal.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        userRepository.deleteById(id);
    }

    public List<User> getUsersByIds(Iterable<Long> ids) {
        return userRepository.findAllById(ids);
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> mapper.map(user, UserDTO.class)).toList();
    }
}

