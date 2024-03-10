package com.guorong.mvc.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    private String name;
    private Integer age;
}
