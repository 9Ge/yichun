package com.enercomn.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultMsg2<T> {
    private int resultCode;
    private String resultMessage;
    private T resultObject;

    public ResultMsg2() {
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


    @Override
    public String toString() {
        return "{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultObject=" + resultObject +
                '}';
    }
}
