package com.ttd.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    ACTIVATED(), PENDING(), SUSPENDED(), DEACTIVATE();
}
