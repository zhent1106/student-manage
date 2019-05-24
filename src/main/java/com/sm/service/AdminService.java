package com.sm.service;

import com.sm.entity.ResultEntity;

/**
 * Created by 田震 on 2019/5/20 17:30
 */
public interface AdminService {
    ResultEntity adminLogin(String account, String password);
}
