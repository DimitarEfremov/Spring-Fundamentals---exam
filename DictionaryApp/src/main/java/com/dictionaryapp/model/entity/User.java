package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Email
    private String email;

    @OneToMany
    private List<Word> addedWords;
}
