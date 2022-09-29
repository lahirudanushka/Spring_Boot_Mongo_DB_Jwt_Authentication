package com.demo.jwtdemo.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

@Document("users")
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Users {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String userName;
    private String password;
    private String email;
    private Integer status;
    private Integer attempts;
    @CreatedDate
    private Date createdDate;
}
