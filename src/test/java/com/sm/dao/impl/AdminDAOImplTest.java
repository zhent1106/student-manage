package com.sm.dao.impl;

import com.sm.dao.AdminDAO;
import com.sm.entity.Admin;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by 田震 on 2019/5/20 17:11
 */
public class AdminDAOImplTest {
    private AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();
    @Test
    public void getAdminByAccount () {
        try {
            Admin admin = adminDAO.getAdminByAccount("tianzhen");
            if (admin != null) {
                System.out.println(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }