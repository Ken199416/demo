package com.example.data;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author 郝明鉴
 * Data 2020/7/21 2:21 下午
 * Version 1.0
 **/

public class SingleData {
    private static List<ExcelData> excelData = new ArrayList<>();
    private static List<ExcelData> preData = new ArrayList<>();
    private static List<ExcelData> postData = new ArrayList<>();
    private static Map<String,List<ExcelData>> excelMap = new HashMap<>();
    public static int totalRowNum = 0;
    public static int endRowNum = 0;
    private SingleData(){

    }
    public static void setExcelData(ExcelData data){
        excelData.add(data);
    }

    public static List<ExcelData> getExcelData(){
        return excelData;
    }


    public static void setPreData(ExcelData excelData){
        preData.add(excelData);
    }

    public static List<ExcelData> getPreData(){
        return preData;
    }


    public static void setPostData(ExcelData excelData){
        postData.add(excelData);
    }

    public static List<ExcelData> getPostData(){
        return postData;
    }


    public static void setExcelMap(String key, List<ExcelData> value){
        excelMap.put(key,value);
    }

    public static Map<String,List<ExcelData>> getExcelMap(){
        return excelMap;
    }


    public static void updateExcelData(int index, ExcelData data){

    }
}
