package com.sm.dao;

import com.sm.entity.Admin;

import java.sql.SQLException;

/**
 * Created by 田震 on 2019/5/20 17:05
 */
public abstract interface AdminDAO {
    /**
     * 根据账号查找管理员
     * @param account
     * @return Admin
     * @throws SQLException
     */
    Admin getAdminByAccount(String account) throws SQLException;
}
