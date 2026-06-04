package com.mizore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mizore.constant.MessageConstant;
import com.mizore.constant.PasswordConstant;
import com.mizore.constant.StatusConstant;
import com.mizore.context.BaseContext;
import com.mizore.entity.Employee;
import com.mizore.exception.AccountNotFoundException;
import com.mizore.mapper.EmployeeMapper;
import com.mizore.service.EmployeeService;
import com.mizore.vo.EmployeeAddReqVO;
import com.mizore.vo.EmployeeLoginReqVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * 员工管理
     * @param reqVO
     * @return
     */

    public Employee login(EmployeeLoginReqVO reqVO) {

        String username = reqVO.getUsername();
        String password = reqVO.getPassword();


        // 1. 查询数据库
        Employee employee = employeeMapper.selectOne(
                new LambdaQueryWrapper<Employee>()
                        .eq(Employee::getUsername, username)
        );


        // 2. 账号不存在
        if (employee == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 3. 密码比对 (MD%加密后比较)
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!md5Password.equals(employee.getPassword())) {
            throw new AccountNotFoundException(MessageConstant.PASSWORD_ERROR);
        }

        // 4. 账号状体校验
        if (StatusConstant.DISABLE.equals(employee.getStatus())) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_LOCKED);
        }

        // 5. 返回实体对象
        return employee;
    }


    /**
     * 新增员工
     * @param reqVO
     */
    public void add(EmployeeAddReqVO reqVO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(reqVO, employee);

        employee.setStatus(StatusConstant.ENABLE);

        // 设置默认密码
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        employeeMapper.insert(employee);
    }
}
