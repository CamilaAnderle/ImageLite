package io.github.CamilaAnderle.imageliteapi.application.users;

import io.github.CamilaAnderle.imageliteapi.domain.entity.User;
import io.github.CamilaAnderle.imageliteapi.domain.exception.DuplicatedTupleException;
import io.github.CamilaAnderle.imageliteapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity save(@RequestBody UserDTO dto){
        try {
            User user = userMapper.mapToUser(dto);
            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (DuplicatedTupleException e){
            Map<String, String> jsonResultado = Map.of( "error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonResultado);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity authenticate(@RequestBody CredentialsDTO credentials){
        var token = userService.autheticate(credentials.getEmail(), credentials.getPassword());
        if(token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(token);
    }
}
