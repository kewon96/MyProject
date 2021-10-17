package com.myproject.api;

import com.myproject.converter.StringListConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    private String email;

    private String password;

    private String username;

    @Transient
    @Convert(converter = StringListConverter.class)
    private List<String> authorities;
}
