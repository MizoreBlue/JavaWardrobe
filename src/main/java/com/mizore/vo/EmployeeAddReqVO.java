package com.mizore.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeAddReqVO implements Serializable {

    private Long id;

    private String username;

    private String roleName;

    private String phone;

    private String sex;

    private String idNumber;

}
