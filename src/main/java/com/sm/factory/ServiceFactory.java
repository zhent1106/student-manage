package com.sm.factory;

import com.sm.service.AdminService;
import com.sm.service.CClassService;
import com.sm.service.DepartmentService;
import com.sm.service.impl.AdminServiceImpl;
import com.sm.service.impl.CClassServiceImpl;
import com.sm.service.impl.DepartmentServiceImpl;

/**
 * Created by 田震 on 2019/5/20 17:34
 */
public class ServiceFactory {
    public static AdminService getAdminServiceInstance() {
        return new AdminServiceImpl();
    }
    public static DepartmentService getDepartmentServiceInstance() {
        return new DepartmentServiceImpl();
    }
 public static CClassService getCCLssServiceInstance(){return new CClassServiceImpl();
 }
}
