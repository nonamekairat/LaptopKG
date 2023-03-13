package com.example.LaptopKG.dto.User;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserDto {

    String address;
    String phoneNumber;
    String firstName;
    String lastName;

}
