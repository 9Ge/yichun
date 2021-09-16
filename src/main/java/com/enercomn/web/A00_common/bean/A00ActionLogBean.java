package com.enercomn.web.A00_common.bean;

import lombok.Data;

/**
 * Created by wbk on 18/01/30.
 */
@Data
public class A00ActionLogBean {
    /**
     * 日志记录ID
     */
    private String  tailId;

    /**
     * 操作模块
     */
    private String  operateModule;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 操作人
     */
    private String  operateUser;

    /**
     * 操作账号
     */
    private String  loginName;

    /**
     * 操作人名称
     */
    private String  name;

    /**
     * 用户所属区域
     */
    private String areaId;

    /**
     * 用户操作时间
     */
    private String operateDate;

    /**
     * 操作描述
     */
    private String operateDescribe;

    /**
     * 操作IP
     */
    private String operateIp;

    public String getTailId() {
        return tailId;
    }

    public void setTailId(String tailId) {
        this.tailId = tailId;
    }

    public String getOperateModule() {
        return operateModule;
    }

    public void setOperateModule(String operateModule) {
        this.operateModule = operateModule;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateDescribe() {
        return operateDescribe;
    }

    public void setOperateDescribe(String operateDescribe) {
        this.operateDescribe = operateDescribe;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}
