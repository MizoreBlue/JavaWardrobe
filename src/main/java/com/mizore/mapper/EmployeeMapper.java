package com.mizore.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mizore.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工表 Mapper
 *
 * @author MizoreBlue
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {


}
