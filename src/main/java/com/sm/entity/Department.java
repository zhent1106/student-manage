package com.sm.entity;

/**
 * Created by 田震 on 2019/5/21 9:30
 */
public class Department {
    private Integer id;
    private  String  departmentName;
    private String logo;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getDepartmentName () {
        return departmentName;
    }

    public void setDepartmentName (String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLogo () {
        return logo;
    }

    public void setLogo (String logo) {
        this.logo = logo;
    }

    @Override
    public String toString () {
        return departmentName;
    }
}
