package com.sm.factory;

import com.sm.dao.AdminDAO;
import com.sm.dao.CCLassDAO;
import com.sm.dao.DepartmentDAO;
import com.sm.dao.impl.AdminDAOImpl;
import com.sm.dao.impl.CClassDAOImpl;
import com.sm.dao.impl.DepartmentDAOImpl;

/**
 * Created by 田震 on 2019/5/20 17:10
 */
public interface DAOFactory {
    public static AdminDAO getAdminDAOInstance() {
        return new AdminDAOImpl();
    }
    public static DepartmentDAO getDepartmentDAOInstance() {
        return new DepartmentDAOImpl();
    }
    public  static CCLassDAO getCCLassDAOInstance(){
        return  new CClassDAOImpl() ;

    }
}
