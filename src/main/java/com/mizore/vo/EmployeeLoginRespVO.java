package com.mizore.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理后台 - 员工管理 Response VO")
public class EmployeeLoginRespVO implements Serializable {


    @Schema(description = "员工ID")
    private Long id;

    @Schema(description = "员工姓名")
    private String userName;

    @Schema(description = "员工角色")
    private String roleName;

    @Schema(description = "员工ID")
    private String token;

}
