package com.enercomn.utils;

import com.github.s7connector.api.DaveArea;
import com.github.s7connector.api.S7Connector;
import com.github.s7connector.api.factory.S7ConnectorFactory;
import com.github.s7connector.impl.nodave.Result;
import com.serotonin.modbus4j.code.DataType;
import lombok.extern.slf4j.Slf4j;

import java.net.Socket;


@Slf4j
public class S7Utils {


    private final static String falseFlag = "false";

    private final static String trueFlag = "true";

    byte[] data =  null;

    /**
     * 获取S7Connector
     *
     * @return
     * @throws Exception
     */
    public static S7Connector getConnector(String ip,int port,int rack,int slot)   {
        S7Connector connector = null;
        try {
             connector = S7ConnectorFactory.buildTCPConnector()
                    .withHost(ip)
                    .withPort(port)
                    .withRack(rack) //optional
                    .withSlot(slot) //optional
                    .build();
        }catch (Exception e) {
           e.printStackTrace();
           log.error("getConnector====ip+"+ip+"port====="+port+"============",e);
           return null;
        }
        return connector;
    }

    /**
     * 读常规值
     * @param connector
     * @param offset
     * @param dataType
     * @return
     */
    public static String read(S7Connector connector,int areaNumber,int offset,int dataType){
        //offset = 12;
        try{
            byte[] data = connector.read(DaveArea.DB, areaNumber, 4, offset);
            log.info(offset+":"+data.toString());
            Number number = S7PLCUtils.bytesToValueRealOffset(dataType, data, 0);
            log.info("PLC 地址是== "+offset+"====值=="+number);
            return  StringUtils.getString(number);
        }catch (Exception e){
            log.error("PLC 地址是== "+offset+",DB =="+areaNumber+",读取数据异常：",e);

            if (Result.SOCKET_ERROR.equals(e.getMessage())){
                //如果是此自定义异常，说明是断线引起
                throw new RuntimeException(e);
            }
            return  null;
        }
    }

    /**
     * 读布尔量
     * @param connector
     * @param offset
     * @param dataType
     * @return
     */
    public static String readBoolean(S7Connector connector,int areaNumber,int offset,int offsetChild,int dataType){
        //offset = 12;
        //int offsetChild = 0;
        try{
            byte[] data = connector.read(DaveArea.DB, areaNumber, 4, offset);
            log.info(offset+":"+data);
            Boolean booleanValue = S7PLCUtils.bytesToValuBooleanOffset(data,offsetChild);
            log.info("PLC 地址是== "+offset+"====值=="+booleanValue);
            if(booleanValue){
                return trueFlag;
            }else{
                return  falseFlag;
            }
        }catch (Exception e ){
            log.error("PLC 地址是== "+offset+"."+offsetChild+",DB =="+areaNumber+",读取数据异常：",e);
            if (Result.SOCKET_ERROR.equals(e.getMessage())|| e.getMessage().contains(Result.SOCKET_ERROR)){
                //如果是此自定义异常，说明是断线引起
                throw new RuntimeException(e);
            }else{
                log.error("e.getMessage()77777:",e);
            }
            return  null;
        }

    }

    /**
     * 写常规值
     * @param connector
     * @param offset
     * @return
     */
    public static String write(S7Connector connector,int areaNumber,int offset,Number value,int dataType){

        try {
            connector.write(DaveArea.DB, areaNumber,  offset,S7PLCUtils.convertToBytes(S7PLCUtils.valueToShorts(dataType,value)));//上限 32767
            return "true";
        }catch (Exception e){
            log.error("write===",e);
            return e.getLocalizedMessage();
        }
    }

    /**
     * 写布尔量
     * @param connector
     * @param offset
     * @return
     */
    public static String writeBoolean(S7Connector connector,int areaNumber,int offset,int offsetChild,boolean value){
        try{
            byte[] data = connector.read(DaveArea.DB, areaNumber, 4, offset);
            connector.write(DaveArea.DB, areaNumber,  offset,S7PLCUtils.insert(value,data,0,offsetChild));
            return "true";
        }catch (Exception e) {
            log.error("writeBoolean===",e);
            return e.getLocalizedMessage();
        }

    }


    /**
     * 获取数据类型
     * @param numberType
     * @return
     */
    public static Integer selectS7DataType( Integer numberType){
        Integer dataType = 0;
            switch (numberType) {
                case 1://双字节有符号
                    dataType = S7PLCUtils.TWO_BYTE_INT_SIGNED;
                    break;
                case 2://双字节无符号
                    dataType = S7PLCUtils.TWO_BYTE_INT_UNSIGNED;
                    break;
                case 3://四字节浮点
                    dataType = S7PLCUtils.FOUR_BYTE_FLOAT;
                    break;
                case 4://四字节无符号
                    dataType = S7PLCUtils.FOUR_BYTE_INT_UNSIGNED;
                    break;
                case 5://四字节有符号
                    dataType = S7PLCUtils.FOUR_BYTE_INT_SIGNED;
                    break;
                default://四字节浮点
                    dataType = DataType.FOUR_BYTE_FLOAT;
                    break;
            }
        log.info("dataType："+dataType+">>numberType:"+numberType);
        return dataType;
    }


    /**
     * 获取数据类型
     * @param dbArea
     * @return
     */
    public static DaveArea selectS7DaveArea( Integer dbArea){

        DaveArea   daveArea = null;
        switch (dbArea) {
            case 6:
                daveArea = DaveArea.ANALOGINPUTS200;
                break;
            case 7:
                daveArea = DaveArea.ANALOGOUTPUTS200;
                break;
            case 28:
                daveArea = DaveArea.COUNTER;
                break;
            case 30:
                daveArea = DaveArea.COUNTER200;
                break;
            case 132:
                daveArea = DaveArea.DB;
                break;
            case 133:
                daveArea = DaveArea.DI;
                break;
            case 131:
                daveArea = DaveArea.FLAGS;
                break;
            case 129:
                daveArea = DaveArea.INPUTS;
                break;
            case 134:
                daveArea = DaveArea.LOCAL;
                break;
            case 130:
                daveArea = DaveArea.OUTPUTS;
                break;
            case 128:
                daveArea = DaveArea.P;
                break;
            case 3:
                daveArea = DaveArea.SYSINFO;
                break;
            case 5:
                daveArea = DaveArea.SYSTEMFLAGS;
                break;
            case 29:
                daveArea = DaveArea.TIMER;
                break;
            case 31:
                daveArea = DaveArea.TIMER200;
                break;
            case 135:
                daveArea = DaveArea.V;
                break;
        }

            try{
                if (daveArea == null){
                    throw  new RuntimeException("未匹配到db号！");
                }
            }catch (Exception e){
                log.error("查询db号异常！",e);
            }

        log.info("daveArea："+daveArea+">>numberType:"+dbArea);
        return daveArea;
    }

    public static Boolean isServerClose(Socket socket){
        try{
            socket.sendUrgentData(0xFF);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        }catch(Exception se){
            return true;
        }
    }

    public static String binaryToDecimal(long n) {
        return String.format("%032d",Long.valueOf(Long.toBinaryString(n)));
    }

    public static String getValueByBit(long n,int bit) {
        String s = binaryToDecimal(n);
        //转换顺序 从右往左
        int realBit = s.length() - bit-1;
        return StringUtils.getString(s.charAt(realBit));
    }
}
