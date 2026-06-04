package com.mizore.service;

import com.mizore.entity.Employee;
import com.mizore.vo.EmployeeAddReqVO;
import com.mizore.vo.EmployeeLoginReqVO;

public interface EmployeeService {


    /**
     * 员工登录
     * @param reqVO
     * @return
     */
    Employee login(EmployeeLoginReqVO reqVO);


    /**
     * 新增员工
     * @param reqVO
     */
    void add(EmployeeAddReqVO reqVO);
}
