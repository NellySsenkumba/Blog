package org.info.blog.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    Permissions.ADMIN_READ,
                    Permissions.ADMIN_CREATE,
                    Permissions.ADMIN_UPDATE,
                    Permissions.ADMIN_DELETE,
                    Permissions.MANAGER_CREATE,
                    Permissions.MANAGER_DELETE,
                    Permissions.MANAGER_UPDATE,
                    Permissions.MANAGER_READ
            )
    ),
    USER(Collections.emptySet()),
    MANAGER(Set.of(
            Permissions.MANAGER_CREATE,
            Permissions.MANAGER_DELETE,
            Permissions.MANAGER_UPDATE,
            Permissions.MANAGER_READ
    ));
    @Getter
    private final Set<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permissions1 -> new SimpleGrantedAuthority(permissions1.name()))
                .toList();
        authorities.add(new SimpleGrantedAuthority("ROLE "));
        return authorities;

    }
}
