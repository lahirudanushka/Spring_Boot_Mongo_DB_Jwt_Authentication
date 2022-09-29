package com.demo.jwtdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<Users, String> {

    @Query("{userName:'?0'}")
    Users findUserByUserName(String userName);


}