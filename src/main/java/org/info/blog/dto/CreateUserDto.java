package org.info.blog.dto;

import org.springframework.lang.NonNull;

import java.sql.Date;

public record CreateUserDto(
        @NonNull String firstName,
        @NonNull String lastName,
        String middleName,
        @NonNull Date dateOfBirth,
        @NonNull String email,
        @NonNull String password
) {
}
