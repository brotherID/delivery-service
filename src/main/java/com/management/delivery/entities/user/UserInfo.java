package com.management.delivery.entities.user;

import com.management.delivery.entities.base.Auditable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userInfo")
public class UserInfo  extends Auditable {
    @Id
    private String id;
    private String username;
    private String firstname;
    private String email;
    private String password;
}
