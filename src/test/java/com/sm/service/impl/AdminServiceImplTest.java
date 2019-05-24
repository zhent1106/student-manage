package com.sm.service.impl;

import com.sm.entity.ResultEntity;
import com.sm.factory.ServiceFactory;
import com.sm.service.AdminService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 田震 on 2019/5/20 17:33
 */
public class AdminServiceImplTest {
    private AdminService adminService = ServiceFactory.getAdminServiceInstance();
    @Test
    public void adminLogin () {
        ResultEntity resultEntity = adminService.adminLogin("chengdi", "chengdi");
        System.out.println(resultEntity);
    }
}