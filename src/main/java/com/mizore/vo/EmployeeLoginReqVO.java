package com.mizore.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 员工管理 Request VO")
@Data
public class EmployeeLoginReqVO {

    @Schema(description = "员工账号")
    private String username;

    @Schema(description = "密码")
    private String password;

}
