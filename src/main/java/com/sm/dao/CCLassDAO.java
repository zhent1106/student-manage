package com.sm.dao;

import com.sm.entity.CClass;
import com.sm.entity.Department;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

/**
 * Created by 田震 on 2019/5/23 9:11
 */
public interface CCLassDAO {
    /**
     * 按照院系id查询班级
     * @param departmentId
     * @return List<CClass>
     * @throws SQLException
     */
    List <CClass> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     *
     * @param
     * @return
     * @throws SQLException
     */
    int insertClass (CClass cClass) throws SQLException;

    int deleteClassById(int id) throws SQLException;
}
