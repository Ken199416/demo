package com.example.data;

import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * @Description TODO
 * @Author 郝明鉴
 * Data 2020/7/21 1:16 下午
 * Version 1.0
 **/
@Data
@ContentRowHeight(17)
@ColumnWidth(13)
public class ExcelData {
    private String no;
    private String name;
//    证件类型
    private String papersType;
//    证件编号
    private String papersNo;
//    企业缴费金额
    private String companyPrice;
//    个人缴费金额
    private String personPrice;
//    总计金额
    private String amountTotal;
//    所属分支机构
    private String organization;
}
