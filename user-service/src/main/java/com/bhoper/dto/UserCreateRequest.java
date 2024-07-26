package com.bhoper.dto;


public record UserCreateRequest(String username, String password, String email,
                                String firstName, String lastName) {
}
