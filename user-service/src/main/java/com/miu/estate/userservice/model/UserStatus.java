package com.miu.estate.userservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    ACTIVATED(Set.of()), PENDING(Set.of()), SUSPENDED(Set.of()), DEACTIVATE(Set.of()),
    ;

    private final Set<Permission> permissions;

//    public List<SimpleGrantedAuthority> getUserStatus() {
//        var authorities =
//                getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                        .collect(Collectors.toList());
//        authorities.add(new SimpleGrantedAuthority("USER_" + this.name()));
//        return authorities;
//    }

    public static Optional<UserStatus> find(String value) {
        if (value.equals(ACTIVATED.name())) {
            return Optional.of(ACTIVATED);
        } else if (value.equals(PENDING.name())) {
            return Optional.of(PENDING);
        } else if (value.equals(SUSPENDED.name())) {
            return Optional.of(SUSPENDED);
        } else if (value.equals(DEACTIVATE.name())) {
            return Optional.of(DEACTIVATE);
        } else {
            return Optional.empty();
        }
    }
}
