package com.sm.service;
import com.sm.entity.CClass;

import java.util.List;

/**
 * Created by 田震 on 2019/5/23 9:29
 */
public interface CClassService {
    List <CClass> selectByDepartmentId (int departmentId) ;
    int addClass(CClass cClass);
    void deleteCClassById(int id);
}
