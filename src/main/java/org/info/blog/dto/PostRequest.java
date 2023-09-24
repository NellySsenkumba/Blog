package org.info.blog.dto;

import org.springframework.lang.NonNull;

public record PostRequest(@NonNull String title) {
}
