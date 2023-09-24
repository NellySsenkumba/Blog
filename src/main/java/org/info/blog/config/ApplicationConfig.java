package org.info.blog.config;

import lombok.RequiredArgsConstructor;
import org.info.blog.repository.UsersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    private final UsersRepository usersRepository;


    private final System.Logger logger = System.getLogger("App");


    private static final List<UserDetails> SYSTEM_USERS = Arrays.asList(
            new User(
                    "ssenkumbanelson.sn@gmail.com",
                     passwordEncoder.encode("123456789"),
                    Collections.singleton(new SimpleGrantedAuthority("ADMIN"))
            ),
            new User(
                    "user@gmail.com",
                    passwordEncoder.encode("password"),
                    Collections.singleton(new SimpleGrantedAuthority("GUEST"))
            )
    );


    @Bean
    public UserDetailsService userDetailsService() {

        return username ->
                usersRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username ->
//                SYSTEM_USERS
//                        .stream()
//                        .filter(user -> user.getUsername().equals(username))
//                        .findFirst()
//                        .orElseThrow(() -> new UsernameNotFoundException("No user was Found"));
//    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//if encrypted
//        return NoOpPasswordEncoder.getInstance();//not enrypted
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
