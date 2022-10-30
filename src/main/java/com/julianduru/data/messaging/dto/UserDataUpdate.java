package com.julianduru.data.messaging.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataUpdate {


    private String username;


    private String firstName;


    private String lastName;


    private String email;


    private String profilePhotoRef;


}
