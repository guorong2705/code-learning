package com.guorong.provider.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto implements Serializable {

    private String userName;
    private Integer age;
}
