package com.sm.service;

import com.sm.entity.Department;

import java.util.List;

/**
 * Created by 田震 on 2019/5/21 9:59
 */
public interface DepartmentService {
    List<Department> selectAll ();

    void deleteDepartment(int id);
    int addDepartment(Department department);
}
