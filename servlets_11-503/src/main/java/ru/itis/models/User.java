package ru.itis.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String phone;
    private String city;
    private String profession;
    private String password;

    public User(Long id, String name, String surname, int age, String email, String phone, String city, String profession) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.profession = profession;
    }
}
