package com.enercomn.utils;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.locator.BinaryLocator;
import com.serotonin.modbus4j.msg.*;
import lombok.extern.slf4j.Slf4j;

/**
 * modbus通讯工具类,采用modbus4j实现
 *
 * @author lxq
 * @dependencies modbus4j-3.0.3.jar
 * @website https://github.com/infiniteautomation/modbus4j
 */
@Slf4j
public class ModbusUtil {



    public static final int COILSTATUS = 1;
    public static final int INPUTSTATUS = 2;
    public static final int HOLDINGREGISTER = 3;
    public static final int INPUTREGISTER = 4;

    /**
     * 工厂。
     */
    static ModbusFactory modbusFactory;
    static {
        if (modbusFactory == null) {
            modbusFactory = new ModbusFactory();
        }
    }

    /**
     * 获取master
     *
     * @return
     * @throws ModbusInitException
     */
    public static ModbusMaster getMaster(IpParameters parameters)   {
        ModbusMaster master = null;
        try {
            //
            // modbusFactory.createRtuMaster(wapper); //RTU 协议
            // modbusFactory.createUdpMaster(params);//UDP 协议
            // modbusFactory.createAsciiMaster(wrapper);//ASCII 协议
            master = modbusFactory.createTcpMaster(parameters, true);// TCP 协议
            //master.setTimeout(); 默认超时500ms
            //master.setRetries();
            master.init();
        }catch (ModbusInitException e) {

            log.error(e.getMessage()+"=============",e);
            return null;
        }
        return master;
    }

    /**
     * 读取[01 Coil Status 0x]类型 开关数据
     * 重载方法
     * @param slaveId
     *            slaveId
     * @param offset
     *            位置
     * @return 读取值
     * @throws ModbusTransportException
     *             异常
     * @throws ErrorResponseException
     *             异常
     * @throws ModbusInitException
     *             异常
     */
    public static ModbusBean readCoilStatus(int slaveId, int offset, ModbusMaster master,String host) {

        ModbusBean a08ModbusBean = new ModbusBean();
        // 01 Coil Status
        try {
            BaseLocator<Boolean> loc = BaseLocator.coilStatus(slaveId, offset);
            a08ModbusBean.setValue(master.getValue(loc).toString());
        }catch (ModbusTransportException | ErrorResponseException e){
            log.warn("采集错误=====点位是"+offset+e.getMessage()+"采集IP为：==="+host,e);
            a08ModbusBean.setError(e.getMessage().trim());
        }
        return a08ModbusBean;
    }

    /**
     * 读取[02 Input Status 1x]类型 开关数据
     *
     * @param slaveId
     * @param offset
     * @return
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static ModbusBean readInputStatus(int slaveId, int offset, ModbusMaster master,String host){
        // 02 Input Status
        ModbusBean a08ModbusBean = new ModbusBean();
        try {
            BaseLocator<Boolean> loc = BaseLocator.inputStatus(slaveId, offset);
            a08ModbusBean.setValue(master.getValue(loc).toString());
        }catch (ModbusTransportException e){
            log.warn("采集错误=====点位是"+offset+e.getMessage()+"采集IP为：==="+host,e);
            a08ModbusBean.setError(e.getMessage().trim());
        }catch (ErrorResponseException e){
            log.warn("采集错误=====点位是"+offset+e.getMessage()+"采集IP为：==="+host,e);
            a08ModbusBean.setError(e.getMessage().trim());

        }
        return a08ModbusBean;
    }

    /**
     * 读取[03 Holding Register类型 2x]模拟量数据
     *
     * @param slaveId
     *            slave Id
     * @param offset
     *            位置
     * @param dataType
     *            数据类型,来自com.serotonin.modbus4j.code.DataType
     * @return
     * @throws ModbusTransportException
     *             异常
     * @throws ErrorResponseException
     *             异常
     * @throws ModbusInitException
     *             异常
     */
    public static ModbusBean readHoldingRegister(int slaveId, int offset, int dataType, ModbusMaster master,String host){
        ModbusBean a08ModbusBean = new ModbusBean();
        try {
            // 03 Holding Register类型数据读取
            BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveId, offset, dataType);

            a08ModbusBean.setValue(master.getValue(loc).toString());
        }catch (ModbusTransportException e){
            log.warn("采集错误=====点位是"+offset+"采集IP为：==="+host,e.getMessage());
            a08ModbusBean.setError(e.getMessage().trim());
        }catch (ErrorResponseException e){
            log.warn("采集错误=====点位是"+offset+"采集IP为：==="+host,e.getMessage());
            a08ModbusBean.setError(e.getMessage().trim());

        }
        return a08ModbusBean;
    }

//    public static void writeHoldingRegister(int slaveId, int offset, int dataType,IpParameters parameters){
//        ModbusBean a08ModbusBean = new ModbusBean();
//        try {
//            // 03 Holding Register类型数据读取
//            BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveId, offset, dataType);
//            getMaster(parameters).setValue(loc,18.225);
//
//            a08ModbusBean.setValue(getMaster(parameters).getValue(loc).toString());
//        }catch (ModbusTransportException e){
//            log.error(e.getMessage());
//            a08ModbusBean.setError(e.getMessage().trim());
//        }catch (ErrorResponseException e){
//            log.error(e.getMessage());
//            a08ModbusBean.setError(e.getMessage().trim());
//
//        }
//    }

    /**
     * 读取[04 Input Registers 3x]类型 模拟量数据
     *
     * @param slaveId
     *            slaveId
     * @param offset
     *            位置
     * @param dataType
     *            数据类型,来自com.serotonin.modbus4j.code.DataType
     * @return 返回结果
     * @throws ModbusTransportException
     *             异常
     * @throws ErrorResponseException
     *             异常
     * @throws ModbusInitException
     *             异常
     */
    public static ModbusBean readInputRegisters(int slaveId, int offset, int dataType, ModbusMaster master,String host) {
        ModbusBean a08ModbusBean = new ModbusBean();
        try {
            // 04 Input Registers类型数据读取
            BaseLocator<Number> loc = BaseLocator.inputRegister(slaveId, offset, dataType);
            a08ModbusBean.setValue(master.getValue(loc).toString());
        }catch (ModbusTransportException e){
            log.warn("采集错误=====点位是"+offset+"采集IP为：==="+host,e.getMessage());
            a08ModbusBean.setError(e.getMessage().trim());
        }catch (ErrorResponseException e){
            log.warn("采集错误=====点位是"+offset+"采集IP为：==="+host,e.getMessage());
            a08ModbusBean.setError(e.getMessage().trim());

        }
        return a08ModbusBean;
    }

    /**
     * 批量读取使用方法
     *
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static BatchResults<Integer> batchRead(IpParameters parameters, BatchRead<Integer> batch)  {
        BatchResults<Integer> results =null;
        try {
            ModbusMaster master = getMaster(parameters);
            batch.setContiguousRequests(false);
            results = master.send(batch);
        }catch (Exception e){
            log.error("batchRead====",e);
            return null;
        }

        return results;
    }

    /**
     * 批量读取使用方法
     *
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static BatchResults<Integer> batchRead(BatchRead<Integer> batch, ModbusMaster master)  {
        BatchResults<Integer> results =null;
        try {
            batch.setContiguousRequests(false);
            results = master.send(batch);
        }catch (Exception e){
            log.error("batchRead====",e);
            return null;
        }

        return results;
    }

    /**
     * 批量读取使用方法
     *
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static BatchResults<Integer> batchRead(ModbusMaster master, BatchRead<Integer> batch) throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        BatchResults<Integer> results = master.send(batch);
        return results;
    }

    /**
     * 写 [01 Coil Status(0x)]写一个 function ID = 5
     *
     * @param slaveId
     *            slave的ID
     * @param writeOffset
     *            位置
     * @param writeValue
     *            值
     * @return 是否写入成功
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    public static boolean writeCoil(ModbusMaster master,int slaveId, int writeOffset, boolean writeValue)
            throws ModbusTransportException, ModbusInitException {

        // 创建请求
        WriteCoilRequest request = new WriteCoilRequest(slaveId, writeOffset, writeValue);
        // 发送请求并获取响应对象
        WriteCoilResponse response = (WriteCoilResponse) master.send(request);
        if (response.isException()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 写[01 Coil Status(0x)] 写多个 function ID = 15
     *
     * @param slaveId
     *            slaveId
     * @param startOffset
     *            开始位置
     * @param bdata
     *            写入的数据
     * @return 是否写入成功
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    public static boolean writeCoils(ModbusMaster master,int slaveId, int startOffset, boolean[] bdata)
            throws ModbusTransportException, ModbusInitException {
        // 创建请求
        WriteCoilsRequest request = new WriteCoilsRequest(slaveId, startOffset, bdata);
        // 发送请求并获取响应对象
        WriteCoilsResponse response = (WriteCoilsResponse) master.send(request);
        if (response.isException()) {
            return false;
        } else {
            return true;
        }

    }

    /***
     * 写[03 Holding Register(4x)] 写一个 function ID = 6
     *
     * @param slaveId
     * @param writeOffset
     * @param writeValue
     * @return
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    public static boolean writeRegister(ModbusMaster master,int slaveId, int writeOffset, short writeValue)
            throws ModbusTransportException, ModbusInitException {
        // 创建请求对象
        WriteRegisterRequest request = new WriteRegisterRequest(slaveId, writeOffset, writeValue);
        WriteRegisterResponse response = (WriteRegisterResponse) master.send(request);
        if (response.isException()) {
            log.error(response.getExceptionMessage());
            return false;
        } else {
            return true;
        }

    }

    /**
     *
     * 写入[03 Holding Register(4x)]写多个 function ID=16
     *
     * @param slaveId
     *            modbus的slaveID
     * @param startOffset
     *            起始位置偏移量值
     * @param sdata
     *            写入的数据
     * @return 返回是否写入成功
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    public static boolean writeRegisters(ModbusMaster master,int slaveId, int startOffset, short[] sdata)
            throws ModbusTransportException, ModbusInitException {
        // 创建请求对象
        WriteRegistersRequest request = new WriteRegistersRequest(slaveId, startOffset, sdata);
        // 发送请求并获取响应对象
        ModbusResponse response = master.send(request);
        if (response.isException()) {
            log.error(response.getExceptionMessage());
            return false;
        } else {
            return true;
        }
    }

    /**
     * 写入数字类型的模拟量（如:写入Float类型的模拟量、Double类型模拟量、整数类型Short、Integer、Long）
     *
     * @param slaveId
     * @param offset
     * @param value
     *            写入值,Number的子类,例如写入Float浮点类型,Double双精度类型,以及整型short,int,long
     *            ,com.serotonin.modbus4j.code.DataType
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static String writeHoldingRegister(ModbusMaster master, int slaveId, int offset, Number value, int dataType)
    {
        // 类型
        try{
            BaseLocator<Number> locator = BaseLocator.holdingRegister(slaveId, offset, dataType);
            master.setValue(locator, value);
            return "true";
        }catch (Exception e){
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    public static String writeHoldingRegisterBit(ModbusMaster master, int slaveId, int offset, int bit,boolean value)
    {
        // 类型
        try{
            BaseLocator<Boolean> locator = BinaryLocator.holdingRegisterBit(slaveId,offset,bit);
            System.out.println(locator.getDataType());
            master.setValue(locator, value);
            return "true";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public static ModbusBean readModBusValue(int pointType,int deviceType ,int addressCode,String childAddressCode,int transId,int numberType,ModbusMaster master,String host){
        ModbusBean modbusBean = null;
        if (COILSTATUS == pointType) {
            modbusBean= readCoilStatus(deviceType, addressCode,master,host);
        } else if (INPUTSTATUS == pointType) {
            modbusBean= readInputStatus(deviceType, addressCode,master,host);
        } else if (HOLDINGREGISTER == pointType) {
            if(StringUtils.isNotEmpty(childAddressCode)){
                modbusBean = binaryLocator(deviceType,addressCode,Integer.parseInt(childAddressCode),master,host);
            }else{
                modbusBean= readHoldingRegister(deviceType, addressCode, CommUtil.selectModBusNumberType(transId, numberType),master,host);
            }
        } else if (INPUTREGISTER == pointType) {
            modbusBean= readInputRegisters(deviceType, addressCode, CommUtil.selectModBusNumberType(transId, numberType),master,host);
        }
        return modbusBean;
    }

    public static ModbusBean binaryLocator(int slaveId, int offset, int bit, ModbusMaster master,String host){
        ModbusBean modbusBean = new ModbusBean();
        try {
            // 03 Holding Register类型数据读取
            BaseLocator<Boolean> loc = BinaryLocator.holdingRegisterBit(slaveId,offset,bit);
            modbusBean.setValue(master.getValue(loc).toString());
        }catch (ModbusTransportException e){
            log.warn("采集错误=====点位是"+offset+"采集IP为：==="+host,e.getMessage());
            modbusBean.setError(e.getMessage().trim());
        }catch (ErrorResponseException e){
            log.warn("采集错误=====点位是"+offset+"采集IP为：==="+host,e.getMessage());
            modbusBean.setError(e.getMessage().trim());

        }
        return modbusBean;
    }



    /**
     * 测试
     *
     * @param args
     */
//    public static void main(String[] args) {
//        try {
//            // 01测试
//           // Boolean v011 = readCoilStatus(1, 0);
//           /* Boolean v012 = readCoilStatus(1, 1);
//            Boolean v013 = readCoilStatus(1, 6);
//           */
//           //System.out.println("00000:" + v011);
//            /*System.out.println("v012:" + v012);
//            System.out.println("v013:" + v013);*/
//            // 02测试
//            /*Boolean v021 = readInputStatus(1, 0);
//            Boolean v022 = readInputStatus(1, 1);
//            Boolean v023 = readInputStatus(1, 2);
//            System.out.println("v021:" + v021);
//            System.out.println("v022:" + v022);
//            System.out.println("v023:" + v023);*/
//
//            IpParameters ipParameters =new IpParameters();
//            ipParameters.setHost("192.168.3.161");
//            ipParameters.setPort(502);
////            // 03测试
    //     A08ModbusBean v031 = readHoldingRegister(1, 0, DataType.TWO_BYTE_INT_SIGNED,ipParameters);// 注意整数
////            A08ModbusBean v032 = readHoldingRegister(1, 1, DataType.TWO_BYTE_INT_SIGNED,ipParameters);// 注意整数
//////            String v032 = readHoldingRegister(1, 2, DataType.TWO_BYTE_INT_UNSIGNED,ipParameters);// 注意整数反转
//////            String v033 = readHoldingRegister(1, 4, DataType.TWO_BYTE_INT_UNSIGNED,ipParameters);// 注意浮点
////
//////            boolean v033 = readInputStatus(1, 0,ipParameters);// 注意浮点
//////            String v032 = readInputRegisters(1, 1, DataType.TWO_BYTE_INT_UNSIGNED,ipParameters);// 同上
//////            Number v033 = readHoldingRegister(1, 2, DataType.TWO_BYTE_INT_UNSIGNED);// 同上
//////            Number v034 = readHoldingRegister(1, 3, DataType.TWO_BYTE_INT_UNSIGNED  );// 同上
////
//////
//////              System.out.println("40001:" + v031);
//////            System.out.println("40002:" + v032);
////            System.out.println("40003:"+v031.getValue());
////            System.out.println("40003:"+v032.getValue());
////            System.out.println("40004:"+v034);
//            // 04测试
//           /* Number v041 = readInputRegisters(1, 0, DataType.FOUR_BYTE_FLOAT);//
//            Number v042 = readInputRegisters(1, 2, DataType.FOUR_BYTE_FLOAT);//
//            System.out.println("v041:" + v041);
//            System.out.println("v042:" + v042);*/
//            // 批量读取
//
//
////            BatchRead<Integer> batch = new BatchRead<Integer>();
////            batch.addLocator(0, BaseLocator.holdingRegister(1, 0, DataType.TWO_BYTE_INT_SIGNED));
////            batch.addLocator(1, BaseLocator.holdingRegister(1, 1, DataType.TWO_BYTE_INT_SIGNED));
////
////            BatchResults<Integer> results = batchRead(ipParameters,batch);
////
//            //writeHoldingRegister(1, 0, DataType.FOUR_BYTE_FLOAT_SWAPPED,ipParameters);// 注意整数
//
////            for(int i=0;i<100;i++) {
////                ModbusBean v031 = readHoldingRegister(1, 0, DataType.FOUR_BYTE_FLOAT_SWAPPED, ipParameters);// 注意整数
////
////                System.out.println("results:" +"============="+ v031.getValue());
////            }
////
////            ModbusBean v031 = readHoldingRegister(1, 40001, DataType.TWO_BYTE_INT_SIGNED,ipParameters);// 注意整数
////            ModbusBean v032 = readHoldingRegister(1, 40002, DataType.TWO_BYTE_INT_SIGNED,ipParameters);// 注意整数
////            ModbusBean v033 = readHoldingRegister(1, 0, DataType.TWO_BYTE_INT_SIGNED,ipParameters);// 注意整数
////
////            ModbusBean v034 = readHoldingRegister(1, 0000, DataType.TWO_BYTE_INT_SIGNED,ipParameters);// 注意整数
////            ModbusBean v035 = readHoldingRegister(1, 2, DataType.TWO_BYTE_INT_SIGNED,ipParameters);// 注意整数
////            System.out.println("40001:" + v031.getValue());
////            System.out.println("40002:" + v032.getValue());
////            System.out.println("0:" + v033.getValue());
////            System.out.println("1:" + v034.getValue());
////            System.out.println("2:" + v035.getValue());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
