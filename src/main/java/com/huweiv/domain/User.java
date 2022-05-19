package com.huweiv.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private List<Role> roles;
    private Integer sex;
    private Integer status;

    public String getSexStr() {
        if (this.sex == 0)
            return "男";
        return "女";
    }

    public String getStatusStr() {
        if (this.status == 1)
            return "启用";
        return "禁用";
    }
}
