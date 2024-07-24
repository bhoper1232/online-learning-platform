package com.bhoper.dto;

import java.time.LocalDate;
import java.util.List;

public record UserCreateRequest(String username, String password, String email,
                                String firstName, String lastName, List<String> roles, LocalDate birtDate) {
}
