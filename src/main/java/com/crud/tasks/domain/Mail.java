package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Builder
@Data
@AllArgsConstructor
public class Mail {
    private final String mailTo;
    private final String toCc;
    private final String subject;
    private final String message;

    public Optional<String> getToCc() {
        return Optional.ofNullable(toCc);
    }

    public Mail(String mailTo, String subject, String message) {
        this(mailTo, subject, message, null);
    }
}
