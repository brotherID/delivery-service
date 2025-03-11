package com.management.delivery.entities.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userInfo")
public class UserInfo {
    @Id
    private String id;
    private String username;
    private String firstname;
    private String email;
    private String password;
}
