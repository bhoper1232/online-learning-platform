package com.bhoper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private ObjectId id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<String> roles;

}
