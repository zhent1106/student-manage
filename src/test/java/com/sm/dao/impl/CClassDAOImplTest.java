package com.sm.dao.impl;

import com.sm.dao.CCLassDAO;
import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 田震 on 2019/5/23 9:20
 */
public class CClassDAOImplTest {
    private CCLassDAO ccLassDAO= DAOFactory.getCCLassDAOInstance();
    @Test
    public void selectByDepartmentId () {
        List<CClass> cClassList = null;
        try {
            cClassList = ccLassDAO.selectByDepartmentId(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass));
    }

    @Test
    public void insertClass () {
       CClass cClass = new CClass();
        cClass.setDepartmentId(5);
        cClass.setClassName("移动1813");
        try {
            int n = ccLassDAO.insertClass(cClass);
            assertEquals(1, n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteClassById () {
        try {
            ccLassDAO.deleteClassById(30);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}