package com.backend.global;

import lombok.Getter;

@Getter
public enum Constant {
    URL("http://localhost:8080");

    private final String url;

    Constant(String url) {
        this.url = url;
    }
}
