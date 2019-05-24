package com.sm.dao.impl;

import com.sm.dao.DepartmentDAO;
import com.sm.entity.Department;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 田震 on 2019/5/21 9:40
 */
public class DepartmentDAOlmplTest {
        private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAOInstance();
    @Test
    public void getAll () {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        departmentList.forEach(department -> System.out.println(department));
    }

    @Test
    public void deleteDepartmentById () throws SQLException {
        int id=8;
        try {
            departmentDAO.deleteDepartmentById(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Test
    public void insertDepartment () {
        Department department = new Department();
        department.setDepartmentName("测试院系");
        department.setLogo("https://student-manage.oss-cn-beijing.aliyuncs.com/logo/47d5fa0d-9517-4cfd-a8cf-a4c1cfbeaf10.jpg");
        try {
            int n = departmentDAO.insertDepartment(department);
            assertEquals(1, n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}