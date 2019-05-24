package com.sm.service.impl;
import com.sm.dao.CCLassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import com.sm.service.CClassService;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 田震 on 2019/5/23 9:30
 */
public class CClassServiceImpl implements CClassService {
    private CCLassDAO ccLassDAO = DAOFactory.getCCLassDAOInstance();
    @Override
    public List<CClass> selectByDepartmentId (int departmentId) {
        List<CClass> cClassList = null;
        try {
            cClassList = ccLassDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            System.err.print("查询院系信息出现异常");
        }
        return cClassList;
    }

    @Override
    public int addClass (CClass cClass) {
        int n = 0;
        try {
            n = ccLassDAO.insertClass(cClass);
        } catch (SQLException e) {
            System.err.print("新增院系信息出现异常");
        }
        return n;
    }

    @Override
    public void deleteCClassById (int id) {
        try {
            ccLassDAO.deleteClassById(id);
        } catch (SQLException e) {
            System.out.println("删除班级信息错误");
        }}
}

