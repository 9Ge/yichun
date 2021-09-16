package com.enercomn.utils;

public class S7Test {

    public static void main(String[] args) {

        String a = "x <  1";
        String[] releArr = a.split(" ");
        System.out.println(releArr.toString());
//        S7Connector connector = S7ConnectorFactory.buildTCPConnector()
//                .withHost("192.168.1.10")
//                .withPort(102)
//                .withRack(0) //optional
//                .withSlot(1) //optional
//                .build();
//        int offset = 12;
//
//
//        byte[] data =  null;

//        offset = 16;
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC word 无符号整数(正整数) 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.TWO_BYTE_INT_UNSIGNED,data,0));
//
//        offset = 4;
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC int 有符号整数 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.TWO_BYTE_INT_SIGNED,data,0));

//        offset = 6;
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC int 有符号整数 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.TWO_BYTE_INT_SIGNED,data,0));

//        offset = 6;
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC real 实数 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.FOUR_BYTE_FLOAT,data,0));
//        offset = 76;
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC Dword 无符号整数(正整数) 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.FOUR_BYTE_INT_UNSIGNED,data,0));
//
//        offset = 84;
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC Dword 无符号整数(正整数) 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.FOUR_BYTE_INT_UNSIGNED,data,0));
//
//        offset = 1;
//        int offsetChild = 3;
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC boolean 地址是== "+offset+"."+offsetChild+"====值=="+S7PLCUtils.bytesToValuBooleanOffset(data,offsetChild));

//        System.out.println("==================写入=====================");
//
//        offset = 12;
//        connector.write(DaveArea.DB, 1,  offset,S7PLCUtils.convertToBytes(S7PLCUtils.valueToShorts(S7PLCUtils.TWO_BYTE_INT_SIGNED,32767)));//上限 32767
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC int 有符号整数 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.TWO_BYTE_INT_SIGNED,data,0));
//
//        offset = 36;
//        connector.write(DaveArea.DB, 1,  offset,S7PLCUtils.convertToBytes(S7PLCUtils.valueToShorts(S7PLCUtils.TWO_BYTE_INT_UNSIGNED,65535)));//上限 65535
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC word 无符号整数(正整数) 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.TWO_BYTE_INT_UNSIGNED,data,0));
//
//        offset = 68;
//        connector.write(DaveArea.DB, 1,  offset,S7PLCUtils.convertToBytes(S7PLCUtils.valueToShorts(S7PLCUtils.FOUR_BYTE_FLOAT,268435455.35)));
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC real 实数 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.FOUR_BYTE_FLOAT,data,0));
//
//        offset = 80;
//        connector.write(DaveArea.DB, 1,  offset,S7PLCUtils.convertToBytes(S7PLCUtils.valueToShorts(S7PLCUtils.FOUR_BYTE_INT_UNSIGNED,268435455)));
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC Dword 无符号整数(正整数) 地址是== "+offset+"====值=="+S7PLCUtils.bytesToValueRealOffset(S7PLCUtils.FOUR_BYTE_INT_UNSIGNED,data,0));
//
//        offset = 1;
//        offsetChild = 3;
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        connector.write(DaveArea.DB, 1,  offset,S7PLCUtils.insert(false,data,0,offsetChild));
//        data = connector.read(DaveArea.DB, 1, 4, offset);
//        System.out.println("PLC boolean 地址是== "+offset+"."+offsetChild+"====值=="+S7PLCUtils.bytesToValuBooleanOffset(data,offsetChild));

    }
}
