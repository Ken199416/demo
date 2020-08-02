package com.example.listener;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author 郝明鉴
 * Data 2020/8/2 10:18 上午
 * Version 1.0
 **/

public class MyCellWriteHandler implements CellWriteHandler {

//    黄色标志的集合,行，列
    private Map<Integer,List<Integer>> cells;

    public MyCellWriteHandler(Map<Integer,List<Integer>> cells){
        this.cells = cells;
    }

    public MyCellWriteHandler(){

    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        if (!cells.isEmpty()) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
            CellStyle cellStyle = workbook.createCellStyle();
            //标黄,要一起设置
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); //设置前景填充样式
            cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());//前景填充色
//            先判断是否有行，然后再判断是否有列
            if (cells.containsKey(cell.getRowIndex()) && cells.get(cell.getRowIndex()).contains(cell.getColumnIndex())) {
                cell.setCellStyle(cellStyle);
            }
        }
    }
}
