package com.mizore.controller.admin;


import com.mizore.common.constant.JwtClaimsConstant;
import com.mizore.entity.Employee;
import com.mizore.common.properties.JwtProperties;
import com.mizore.service.EmployeeService;
import com.mizore.common.utils.JwtUtil;
import com.mizore.common.utils.Result;
import com.mizore.vo.EmployeeAddReqVO;
import com.mizore.vo.EmployeeLoginReqVO;
import com.mizore.vo.EmployeeLoginRespVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 员工管理
 */
@Slf4j
@Tag(name = "管理后台 - 员工管理")
@RestController
@RequestMapping("/admin/user")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 员工登录
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "员工登录")
    public Result<EmployeeLoginRespVO> login(@RequestBody EmployeeLoginReqVO reqVO) {
        log.info("员工登录：{}", reqVO);
        Employee employee = employeeService.login(reqVO);


        // 登录成功后，生成jwt令牌
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims
        );

        EmployeeLoginRespVO respVO = EmployeeLoginRespVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .roleName(employee.getRoleName())
                .token(token)
                .build();

        return Result.success(respVO);
    }


    /**
     * 新增员工
     * @return
     */
    @PostMapping
    @Operation(summary = "新增员工")
    public Result addEmployee(@RequestBody EmployeeAddReqVO reqVO) {
        log.info("新增员工:{}", reqVO);
        employeeService.add(reqVO);
        return Result.success();
    }

}
