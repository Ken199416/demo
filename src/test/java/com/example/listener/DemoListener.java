package com.example.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.data.ExcelData;
import com.example.data.SingleData;
import org.apache.poi.util.StringUtil;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 郝明鉴
 * Data 2020/7/21 1:48 下午
 * Version 1.0
 **/

public class DemoListener extends AnalysisEventListener<ExcelData> {
    private Logger logger = LoggerFactory.getLogger(DemoListener.class);
    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        logger.info(analysisContext.toString());
        int rowIndex = analysisContext.readRowHolder().getRowIndex();
        if (rowIndex < 5){
//            开始，模板开始的地方,小于5，具体要看excel中的具体情况
            SingleData.setPreData(excelData);
        }else if (StringUtils.isNotBlank(excelData.getNo()) && excelData.getNo().equals("合计")){
//            获取到合计的行数,并记录
            SingleData.totalRowNum = rowIndex;
        }else if (StringUtils.isNotBlank(excelData.getNo()) && excelData.getNo().equals("结束")){
//            获取到结束的行数，记录
            SingleData.endRowNum = rowIndex;
            ExcelData endExcelData1 = new ExcelData();
            endExcelData1.setNo("结束");
            endExcelData1.setName("如果在提交表单时本行以上部分存在空行，请将空行删除。");
            ExcelData endExcelData2 = new ExcelData();
//            如果是"",此行会被忽略
            endExcelData2.setNo(" ");
            SingleData.setPostData(endExcelData1);
            SingleData.setPostData(endExcelData2);
        }else if (SingleData.totalRowNum != 0 && rowIndex > SingleData.totalRowNum && SingleData.endRowNum == 0){
//            处理分组数据，合计的行数被记录了，并且大于合并的行数，并且结束的行数还没有被记录
            SingleData.setExcelData(excelData);
        }else if (SingleData.endRowNum != 0){
//            处理模板结束的数据，当结束的行数被记录时,如果第一列是空的  要给" " 否则会清除这一行
            if (StringUtils.isBlank(excelData.getNo())){
                excelData.setNo("meiyou");
            }
            SingleData.setPostData(excelData);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info("数据解析完成");
    }

    public void executeData(){

    }
}
