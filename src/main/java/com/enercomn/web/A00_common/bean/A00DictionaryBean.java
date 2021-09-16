package com.enercomn.web.A00_common.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author Oliver
 * @version 1.0
 * @class_name A00DictionaryBean
 * @date 2019-05-30 13:48
 * @description 字典表
 */
@Data
public class A00DictionaryBean {

    /**
     * 主键ID
     */
    private int cTdId;
    /**
     * 字典ID
     */
    private int cDicId;
    /**
     * 字典类型
     */
    private String cDicType;
    /**
     * 字典描述
     */
    private String cDicDesc;
    /**
     * 创建人
     */
    private String cCreateUser;
    /**
     * 创建时间
     */
    private Date cCreateTime;
    /**
     * 更新人
     */
    private String cUpdateUser;
    /**
     * 更新时间
     */
    private Date cUpdateTime;
    /**
     * 是否删除 0：未删除 1：删除
     */
    private int isDel;

    public int getcTdId() {
        return cTdId;
    }

    public void setcTdId(int cTdId) {
        this.cTdId = cTdId;
    }

    public int getcDicId() {
        return cDicId;
    }

    public void setcDicId(int cDicId) {
        this.cDicId = cDicId;
    }

    public String getcDicType() {
        return cDicType;
    }

    public void setcDicType(String cDicType) {
        this.cDicType = cDicType;
    }

    public String getcDicDesc() {
        return cDicDesc;
    }

    public void setcDicDesc(String cDicDesc) {
        this.cDicDesc = cDicDesc;
    }

    public String getcCreateUser() {
        return cCreateUser;
    }

    public void setcCreateUser(String cCreateUser) {
        this.cCreateUser = cCreateUser;
    }

    public Date getcCreateTime() {
        return cCreateTime;
    }

    public void setcCreateTime(Date cCreateTime) {
        this.cCreateTime = cCreateTime;
    }

    public String getcUpdateUser() {
        return cUpdateUser;
    }

    public void setcUpdateUser(String cUpdateUser) {
        this.cUpdateUser = cUpdateUser;
    }

    public Date getcUpdateTime() {
        return cUpdateTime;
    }

    public void setcUpdateTime(Date cUpdateTime) {
        this.cUpdateTime = cUpdateTime;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
}
