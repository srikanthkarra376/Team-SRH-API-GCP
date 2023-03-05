package com.collectifjadore.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    private String id;

    private ERole name;


}
