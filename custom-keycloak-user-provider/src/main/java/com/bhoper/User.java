package com.bhoper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private ObjectId id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<String> roles;

//    public Document toDocument() {
//        return new Document("_id", id)
//                .append("username", username)
//                .append("email", email)
//                .append("password", password)
//                .append("firstName", firstName)
//                .append("lastName", lastName)
//                .append("birthDate", birthDate)
//                .append("roles", roles);
//    }
//
//    public static User fromDocument(Document doc) {
//        return new User(
//                doc.getObjectId("_id"),
//                doc.getString("username"),
//                doc.getString("email"),
//                doc.getString("password"),
//                doc.getString("firstName"),
//                doc.getString("lastName"),
//                (LocalDate) doc.get("birthDate"),
//                doc.getList("roles", String.class)
//        );
//    }
}
