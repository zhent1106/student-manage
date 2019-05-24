package com.sm.service.impl;

import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.factory.ServiceFactory;
import com.sm.service.CClassService;
import com.sm.service.DepartmentService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 田震 on 2019/5/23 9:37
 */
public class CClassServiceImplTest {
    private CClassService cClassService = ServiceFactory.getCCLssServiceInstance();
    @Test
    public void selectByDepartmentId () {
        List<CClass> cClassList = cClassService.selectByDepartmentId(3);
        cClassList.forEach(cClass-> System.out.println(cClass));
    }

    @Test
    public void addClass () {
        CClass cClass = new CClass();
        cClass.setDepartmentId(1);
        cClass.setClassName("测试班级");
        int n = 0;
        n = cClassService.addClass(cClass);
        assertEquals(1, n);
    }

    @Test
    public void deleteClass () {
        int id =2;
        cClassService.deleteCClassById(id);
    }

    @Test
    public void deleteCClassById () {
        cClassService.deleteCClassById(35);
    }
}