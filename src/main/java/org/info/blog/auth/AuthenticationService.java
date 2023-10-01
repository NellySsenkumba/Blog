package org.info.blog.auth;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.info.blog.config.ApplicationConfig;
import org.info.blog.config.JwtService;
import org.info.blog.dto.CreateUserDto;
import org.info.blog.dto.LoginDto;
import org.info.blog.exceptions.UserExistsException;
import org.info.blog.models.Role;
import org.info.blog.models.User;
import org.info.blog.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final User user;

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    private final UserDetailsService userDetailsService;

    public AuthenticationResponse register(CreateUserDto request) {
        if (repository.existsByEmail(request.email())) {
            throw new UserExistsException();
        }

        //using builder
//        User user = User.builder()
//                .firstName(request.firstName())
//                .lastName(request.lastName())
//                .email(request.email())
//                .password(passwordEncoder.encode(request.password()))
//                .dateOfBirth(request.dateOfBirth())
//                .middleName(request.middleName())
//                .role(Role.USER)
//                .dateCreated(new Timestamp(System.currentTimeMillis()))
//                .build();

        // using beans
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        user.setDateOfBirth(request.dateOfBirth());
        user.setMiddleName(request.middleName());
        user.setRole(Role.valueOf(request.role()));


        repository.save(user);

        HashMap<String, Object> test = new HashMap<>();

        String jwtToken = jwtService.generateToken(test, user);

        //        getting a claim from the token

//        Claims allClaims = jwtService.extractAllClaims(jwtToken);
//        System.out.println("all claims");
//        System.out.println(allClaims);
//        System.out.println("test");
//        System.out.println(allClaims.get("test"));
//        System.out.println(allClaims.get("sub"));
        Object claims = jwtService.extractClaim(jwtToken, new Function<Claims, Object>() {
                    @Override
                    public Object apply(Claims claims1) {

                        return claims1.get("test");
                    }

                }
        );


        System.out.println(claims);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UserDetails user = userDetailsService.loadUserByUsername(request.email());


        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
