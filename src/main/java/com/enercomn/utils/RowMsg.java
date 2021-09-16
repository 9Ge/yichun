package com.enercomn.utils;

import com.enercomn.Enum.ResultStatusCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * 表格数据返回类型
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RowMsg {
    private int resultCode;
    private String resultMessage;
    private Long totalCount;
    private int currPage;
    private int currCount;
    private Object dataList;
    private Map otherInfo;

    public RowMsg() {
    }

    public RowMsg(int resultCode, String resultMessage, PageBean pageBean) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.totalCount = pageBean.totalCount;
        this.currPage = pageBean.currPage;
        this.currCount = pageBean.currCount;
        this.dataList = pageBean.dataList;
    }

    public RowMsg(Object dataList) {
        this.resultCode = ResultStatusCode.OK.getResultCode();
        this.resultMessage = ResultStatusCode.OK.getResultMessage();
        this.dataList = dataList;
    }

    public RowMsg(int resultCode, String resultMessage, PageBean pageBean, Map otherInfo){
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.totalCount = pageBean.totalCount;
        this.currPage = pageBean.currPage;
        this.currCount = pageBean.currCount;
        this.dataList = pageBean.dataList;
        this.otherInfo = otherInfo;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getCurrCount() {
        return currCount;
    }

    public void setCurrCount(int currCount) {
        this.currCount = currCount;
    }

    public Object getDataList() {
        return dataList;
    }

    public void setDataList(Object dataList) {
        this.dataList = dataList;
    }

    public Map getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Map otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", currPage='" + currPage + '\'' +
                ", currCount='" + currCount + '\'' +
                ", dataList=" + dataList +
                '}';
    }
}
