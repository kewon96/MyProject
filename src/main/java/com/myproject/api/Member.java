package com.myproject.api;

import com.myproject.converter.StringListConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    private String id;

    private String password;

    private String email;

    @Convert(converter = StringListConverter.class)
    private List<String> authorities;
}
