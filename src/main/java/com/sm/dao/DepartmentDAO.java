package com.sm.dao;

import com.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 田震 on 2019/5/21 9:31
 */
public interface DepartmentDAO {
    /**
     *查询院系
     * @return list<Department>
     * @throws SQLException
     */
    List<Department> getAll() throws SQLException;

    int insertDepartment (Department department) throws SQLException;

    int deleteDepartmentById (int id) throws SQLException;
}
