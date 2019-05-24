package com.sm.dao.impl;

import com.sm.dao.CCLassDAO;
import com.sm.entity.CClass;
import com.sm.utils.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 田震 on 2019/5/23 9:12
 */
public class CClassDAOImpl implements CCLassDAO {
    @Override
    public List <CClass> selectByDepartmentId (int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_class WHERE department_id=? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, String.valueOf(departmentId));
        ResultSet rs = pstmt.executeQuery();
        List <CClass> ccLassList = new ArrayList <>();
        while (rs.next()) {
            CClass cClass = new CClass();
            cClass.setId(rs.getInt("id"));
            cClass.setId(rs.getInt("department_id"));
            cClass.setClassName(rs.getString("class_name"));
            ccLassList.add(cClass);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return ccLassList;
    }

    @Override
    public int insertClass (CClass cClass) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_class (department_id,class_name) VALUES (?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, cClass.getDepartmentId());
        pstmt.setString(2, cClass.getClassName());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int deleteClassById (int id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_class WHERE id="+id;
        PreparedStatement pstmt = connection.prepareStatement(sql);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }
}
