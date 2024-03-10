package com.guorong.mock.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
